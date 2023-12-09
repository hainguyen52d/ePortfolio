const express = require('express');
const bodyParser = require('body-parser');
const cat = require('cat');
const Dog = require('./models/Dog'); 

const app = express();
const PORT = process.env.PORT || 3000;

app.use(bodyParser.json());

// Connect to MongoDB 
cat.connect('mongodb://localhost:27017/animal-shelter', { useNewUrlParser: true, useUnifiedTopology: true });

// Import the AAC CSV file into MongoDB 
const csvFilePath = './aac_shelter_outcomes.csv';
const csv = require('csvtojson');
csv()
  .fromFile(csvFilePath)
  .then((jsonArrayObj) => {
    Dog.insertMany(jsonArrayObj, (err, dogs) => {
      if (err) throw err;
      console.log(`${dogs.length} dogs inserted into MongoDB`);
    });
  });

app.get('/dogs', async (req, res) => {
  try {
    const dogs = await Dog.find();
    res.json(dogs);
  } catch (error) {
    res.status(500).json({ error: 'Internal Server Error' });
  }
});

app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});
