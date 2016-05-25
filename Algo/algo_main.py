""" Main algorithm module. Contains script calling algorithm functions defined in supporting modules."""

import rw_helper
import json
import User
import scoring
import admin_helper

if __name__ == '__main__':
	print "*** THE FOLLOWING IS ONLY EXECUTED ONCE TO CREATE THE JSON DATABASE FROM DUMMY CSV ***"
	# load user data base from csv file if directly running this module. 
	print "\nReading user database from csv file..."
	# must be kept in the format specified in the csv file to work with the init_user_object_list function
	# Important: when adding a new user, set all text based forms to "N/A" and int/float based forms to 99.99 if they are not yet known.
	user_matrix = rw_helper.table_reader("user_database.csv")[2:] # exclude headers
	# fake client for testing purposes 
	client_row = ["CL001", "John Doe", "M", "Company Ltd.", "Both", "Product Tester","-79.4823448", "43.752323", "-79.38218056", "43.64316667", "5", "3","4","N/A", "9999", "FM 88.1","FM 101.3","AM 1430","AM 1430","FM 92.5","2","1","3"]
	client = User.User(client_row[:5], client_row[6:10],client_row[5],client_row[10:15], client_row[15:20],client_row[20:])

	# Create user_list (of objects) from matrix
	print "Initializing User objects..."
	# user_list is an array of user objects
	user_list_temp = admin_helper.init_user_object_list(user_matrix)

	print "Write to JSON file..."
	counter = 0
	json_data = []
	for user in user_list_temp:
		obj_dict = rw_helper.jdefault(user)
		json_data.append(obj_dict)

	with open('user_database.json', 'w') as f:
		json.dump(json_data, f)
		f.close()


print "\n*** NOW EXECUTING TEST OF ALL OPERATION ***"

print "\n Load user database from JSON file, deserializing..."

with open('user_database.json', 'r') as f:
	json_dict_import = json.load(f)
	f.close()
print json_dict_import[0]

# make a user_list of objects from json
# pass that into scoring stuff
# spit out results in another json file

print "\nGenerating Scoring Matrix..."
# compatibility index array: (ID, score), (ID, score), ... sorted from highest compatibility to lowest
compatibility_index_array = scoring.full_score_matrix(client, user_list_temp)
print compatibility_index_array[:10]

# generate a list of users from the compatibility array
# pass the user list to the result_list_sorter to sort by a certain criteria 

