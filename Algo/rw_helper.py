"""This module handles reading and writing CSV files."""

import csv
import sys
import json

def table_reader(filename):
	"""Takes filename as input. Returns contents of input in array."""
	table = []
	try:
		input_file = open(filename, "r+")
		reader = csv.reader(input_file)
		for row in reader:
			table.append(row)
		input_file.close()
	except:
		sys.exit("Could not read %s" %(filename))

	return table

def jdefault(obj):
	""" Represents an object as a dictionary of it's attributes. Allows for JSON r/w."""
	return obj.__dict__

def obj_to_JSON_str(obj):
	obj_dict = jdefault(obj)
	json_str = json.dumps(obj_dict)
	return json_str