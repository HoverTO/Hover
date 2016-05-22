"""Module delcares the 'User' class to model users and store their settings."""

class User:

	def __init__(self, basic_info_array, geo_array, optional_info_array = [], pref_array = [], radio_array = [], match_priority_array = []):
		""" 
		Initializes a new user. Sets optional attributes to "N/A" or 99.99 to be filled in later. 
		NOTE: the "match_priority_array" input must be completely filled in or left out entirely!
		"""

		# Initialize (mandatory) basic attributes that all users must input at onboarding.
		self.ID = basic_info_array[0]
		self.name = basic_info_array[1]
		self.gender = basic_info_array[2]
		self.org = basic_info_array[3]
		self.status = basic_info_array[4]
		# Initialize (mandatory) geographic information.
		self.home_long = float(geo_array[0])
		self.home_lat = float(geo_array[1])
		self.dest_long = float(geo_array[2])
		self.dest_lat = float(geo_array[3])
		# Initialize (optional) basic information.
		if optional_info_array != []:  
			self.profession = optional_info_array[0]
		else:
			self.profession = "N/A"
		# Initialize (optional) preferences.
		if pref_array != []:
			self.talk = float(pref_array[0])
			self.ac = float(pref_array[1])
			self.heat = float(pref_array[2])
			self.car_model = pref_array[3]
			self.car_year = int(pref_array[4])
		else:
			self.talk = 99.99
			self.ac = 99.99
			self.heat = 99.99
			self.car_model = "N/A"
			self.car_year = 9999
		# Initialize (optional) radio array
		if radio_array != []: 
			self.radio_array = []
			for radio in radio_array:
				self.radio_array.append(radio)
		else:
			self.radio_array = ["N/A", "N/A", "N/A", "N/A", "N/A",]
		# Initialize (optional) match more preferences
		if match_priority_array != []:
			self.match_priority_array = match_priority_array # These are characters, not ints!
		else:
			self.match_priority_array = ["N/A", "N/A", "N/A"]

	def print_user_summary(self):
		""" For debugging purposes"""

		print "User ID & Name: ", self.ID, " | ", self.name
		print "User Home: ", self.home_lat, ",", self.home_long, " User Dest: ", self.dest_long, ",", self.dest_lat
		print "User preferences - Talk: ", self.talk," A/C: ", self.ac, " Heat: ", self.heat, " Car Model: ", self.car_model, "Car Year: ", self.car_year
		print "User Radio Presets: ", self.radio_array
		print "User match priority: ", self.match_priority_array

