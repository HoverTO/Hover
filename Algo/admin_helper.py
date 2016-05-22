"""
Module contains administrative functions that aid in keeping track of users, creating new ones and summarizing information. 
"""
import User
import progress_bar

def init_user_object_list(user_matrix):
	"""
	Creates a list of user objects from a user data matrix extracted from the database.
	Use when refreshing the list of users to search through.
	"""
	counter = 0
	user_object_list = []
	for user_row in user_matrix:
		temp_user_obj = User.User(user_row[:5], user_row[6:10],user_row[5],user_row[10:15], user_row[15:20],user_row[20:])
		user_object_list.append(temp_user_obj)
		# print progress. REMOVE FOR FINAL IMPLEMENTATION
		counter = counter + 1
		progress_bar.progress(counter, len(user_matrix))
	print "\n"
	return user_object_list	