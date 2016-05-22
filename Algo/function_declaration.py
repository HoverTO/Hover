""" Module holds all functions called in algo_main."""

import User
import progress_bar
from math import radians, cos, sin, asin, sqrt


######################################## Helper Functions ########################################

def haversine_distance(lon1, lat1, lon2, lat2):
    """
    Calculate the great circle distance between two points 
    on the earth (specified in decimal degrees)
    """
    # convert decimal degrees to radians 
    lon1, lat1, lon2, lat2 = map(radians, [lon1, lat1, lon2, lat2])

    # haversine formula 
    dlon = lon2 - lon1 
    dlat = lat2 - lat1 
    a = sin(dlat/2)**2 + cos(lat1) * cos(lat2) * sin(dlon/2)**2
    c = 2 * asin(sqrt(a)) 
    r = 6371 # Radius of earth in kilometers. Use 3956 for miles
    return c * r

######################################## Scoring Functions ########################################

def home_distance_score(client_obj, user_object_list):
	"""
	Computes a score from 0 (furthest from client) to 1 (closest to client). Currently, this score is exponentially scaled i.e.
	score is proportional to the square of the distance.
	Returns an array of (float) scores corresponding to the user_object_list
	"""
	score_array = []
	home_distance_array = []

	for user in user_object_list:
		home_distance_array.append(haversine_distance(client_obj.home_long, client_obj.home_lat, user.home_long, user.home_lat))
	
	max_dist = max(home_distance_array)
	min_dist = min(home_distance_array)
	dist_range = max_dist - min_dist
	
	for distance in home_distance_array:
		score_array.append( 1- ((distance - min_dist)**2/dist_range**2))

	return score_array

def generic_slider_score(client, user_object_list, attribute_name, relation_order):
	score_array = []
	scale_difference = []

	if getattr(client, attribute_name) == 99.99: # if client didn't specify attribute, set score_array to "do not consider"
		score_array = ["DNC"]*len(user_object_list)
	else:
		for user in user_object_list:
			if getattr(user, attribute_name) == 99.99: #if the user didn't specify attribute, set the difference to N/A
				scale_difference.append("N/A")
			else:
				scale_difference.append(abs(getattr(client, attribute_name) - getattr(user, attribute_name)))
		max_diff = max(scale_difference)
		min_diff = min(scale_difference)
		#print scale_difference[:10]
		for diff in scale_difference:
			if diff == "N/A": # if the scale_difference has been marked N/A, do not consider it for scoring
				score_array.append("DNC") # mark this row as "do not consider"
			else:
				score_array.append(1 - ((diff - min_diff)**relation_order/(max_diff - min_diff)**relation_order))

	return score_array

def radio_score(client_obj, user_object_list):
	raw_score_array = []

	# Weighting dictionaries 
	client_rank_weighting = {0:5, 1:4, 2:3, 3:2, 4:1} # index:score multiplier 
	user_proximity_weighting = {0:1, 1:0.75, 2:0.5, 3:0.25, 4:0.1}  # proximity:multiplier

	# Compute raw score
	for user in user_object_list:
		score = 0
		client_index = 0
		for radio in client_obj.radio_array:
			try:
				# if station is found in the user array, credit appropriate score
				# if the station is an "N/A" entry, proceed to next station in client list
				if radio == "N/A":
					continue
				else:
					user_index = user.radio_array.index(radio)
					index_proximity = abs(user_index - client_index)
					score = score + client_rank_weighting[client_index]*user_proximity_weighting[index_proximity]
			except:
				# station not found in in the user radio array, take no action
				pass
			client_index = client_index + 1

		# pass score into the score array
		raw_score_array.append(score)

	# Normalize score array
	score_array = []
	for raw_score in raw_score_array:
		score_array.append((raw_score - min(raw_score_array))/(max(raw_score_array)- min(raw_score_array)))

	return score_array

def full_score_matrix(client_obj, user_object_list):
	"""
	Compiles the score for all available metrics together and weights them by the client's priority array.
	Returns a single weighted array of scores from 0 to 1 indicating overall compatibility.
	"""
	print "Calculating: Home Distance Score"
	home_dist_score_array = home_distance_score(client_obj, user_object_list)
	#print home_dist_score_array[:how 10]

	print "Calculating: Talk Score"
	talk_score_array = generic_slider_score(client_obj, user_object_list, "talk", 1)
	#print talk_score_array[:10]

	print "Calculating: A/C Score"
	ac_score_array = generic_slider_score(client_obj, user_object_list, "ac", 1)
	#print ac_score_array[:10]

	print "Calculating: Heat Score"
	heat_score_array = generic_slider_score(client_obj, user_object_list, "heat", 1)
	#print heat_score_array[:10]

	print "Calculating: Music Score"
	music_score_array = radio_score(client_obj, user_object_list)
	#print music_score_array[:10]
	# performs weighted average of all scores to generate the overall compatibility index

	# DEAL WITH THE DNCs !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	weighting_dictionary = {'1' : 0.3, '2' : 0.1, '3' : 0.1, 'N/A' : 0.33333}
	talk_weight  = weighting_dictionary[client_obj.match_priority_array[0]]
	temp_weight  = weighting_dictionary[client_obj.match_priority_array[1]]
	music_weight  = weighting_dictionary[client_obj.match_priority_array[2]]
	master_score_array = []

	for index in range (len(user_object_list)):
		master_score_array.append(home_dist_score_array[index]*0.5 
			                      + talk_weight*talk_score_array[index]
			                      + temp_weight*0.5*(ac_score_array[index] + heat_score_array[index])
			                      + music_weight*music_score_array[index])

	# Create a compatibility_index_array which houses the user ID alongside the score.
	compatibility_index_array = []
	for index in range(len(user_object_list)):
		compatibility_index_array.append([user_object_list[index].ID, master_score_array[index]])

	# Sort the compatibility array by the second element using an anonymous function
	compatibility_index_array.sort(key = lambda row: row[1])
	compatibility_index_array.reverse()

	return compatibility_index_array




