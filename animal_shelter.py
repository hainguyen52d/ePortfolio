from pymongo import MongoClient
from bson.objectid import ObjectId

class AnimalShelter(object):
    """ """

    def __init__(self, username, password):
        # Init to connect to mongodb without authentication
        #self.client = MongoClient('mongodb://localhost:35531')
        #init connect to mongodb with authentication 
        self.client = MongoClient('mongodb://%s:%s@localhost:35531/?authenMechanism=DEFAULT&authSource=AAC'%(username,password))
        self.database = self.client['AAC']
    def create(self, data):
        if data is not None:
            print("Add a new animal")
            self.database.animals.insert_one(data)
            return True
        else:
            raise Exception("Nothing to save...")

    def read_all(self, data):
        cursor = self.database.animals.find(data, {'_id':False}) ## return a cursor which a pointer to a list of results (Documents)
        return cursor                                           
    
    def read(self, data):
        # returns only one document as a python dictionary
            return self.database.animals.find_one(data)
   
    def update(self, query, newval):
        # updates many existed documents as pyhon dictionary
        # query is the condition
        # newval is the new values that need to be updated
        if query is not None and newval is not None:
            return self.database.animals.update_many(query, newval)
        else: 
            raise Exception("There is no existed animal to update")       
    
    def delete(self, val):                                                           
       #  delete many existed documents
        if val is not None:
            print("Delete animals")
            return self.database.animals.delete_many(val)                                                
        else: 
            raise Exception("There is no existed animal to delete")  