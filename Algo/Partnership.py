"""
Module defines the Partnership class. Partnerships keeps track of User object members, expense sharing, stats and ride
history. Every set of Users who ride together need their own partnership. For example if user A and B ride together but 
sometimes also ride with user C, two partnerships would need to be defined: (A,B) and (A,B,C).
"""

class Partnership:

	def __init__(self, member_list, schedule_list):
		# member_list (mandatory) is a list of user objects who partake in this partnership (2 or more)
		self.member_list = member_list
		# schedule_list (mandatory), like it's definition in the User class, is a list of (weekday, hour, minute) triplets 
		# Note that both Users and Partnerships have schedule_list's - they need not be the same
		self.schedule_list = schedule_list

		self.emissions_saved = 0
		self.money_saved = 0
		self.distance_carpooled = 0

		# Money owed is a dictionary with User ID keys and a monetary value. See trip_made function.
		self.money_owed = {}
		for member in member_list:
			self.money_owed[member.ID] = 0

	def trip_made(distance):
		"""
		This function updates the stats for both the Partnership object and all its member's user objects after the 
		completion of a trip.
		"""
		# HOW DO I DEAL WITH DISTANCES?? AT THE PICKUP POINT?? WHAT IF THERE ARE MULTIPLE PICKUP POINTS??

