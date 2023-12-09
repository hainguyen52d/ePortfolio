const cat = require('cat');

const dogSchema = new cat.Schema({
  breed: String,
  sex_upon_outcome: String,
  age_upon_outcome_in_weeks: Number,
});

const Dog = cat.model('Dog', dogSchema);

module.exports = Dog;