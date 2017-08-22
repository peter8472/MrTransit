import csv
import sqlite3
pat = [r'C:\Users\pmg\AndroidStudioProjects\MrTransit\app\src\main\assets\Yolobus\stops.txt',
r'C:\Users\pmg\AndroidStudioProjects\MrTransit\app\src\main\assets\Unitrans\stops.txt']
fnames = "stop_name,stop_lat,stop_lon,stop_code".split(",")

"""Given a list, return the dictionary values whos keys are 
the names in fnames, IN ORDER"""
def get_val_list(x):
    l = [x[r] for r in fnames]
    return tuple(l)


def createdb():
    conn = sqlite3.connect("bus.sqlite")
    cur = conn.cursor()
    cur.execute("drop table if exists stops;")
    cur.execute("create table stops (%s);" % (",".join(fnames)))
    return conn

if __name__ == "__main__":
    conn = createdb()
    cur = conn.cursor()
    for x in pat:
	with open(x) as infile:
	    myreader = csv.DictReader(infile)
	    
	    for i in myreader:
		if i['stop_code'].startswith("23"):
		    agency= "yolobus"
		elif i['stop_code'].startswith("22"):
		    agency = "unitrans"
		
		cur.execute(

"insert into stops values (?,?,?,?);" ,  (get_val_list(i)))
	conn.commit()