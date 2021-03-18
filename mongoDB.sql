https://fr.blog.businessdecision.com/tutoriel-mongodb-requetes/#:~:text=MongoDB%20est%20une%20base%20de,disponibilit%C3%A9%2C%20tol%C3%A9rance%20aux%20pannes).


mongod
mongo
use mobiledb
db

-- select *  from telephone
db.telephone.find({})

-- select * from telephone where numero=""
db.telephone.find({"numero":"0332914338"})
db.products.drop()
db.myData.updateOne({_id:ObjectId("new_id")},{$set:{name:"new_name"}})

-- db.offre.insert{"nom":"fun","validiter":24,"offre":"appel","duree"=1})

db.client.drop()
db.client.insert(
   [
     { _id:"1",nom: "Iavotiana", email:"iavotiana@gmail.com" },
     { _id:"2",nom: "Faniry", email:"faniry@gmail.com" },
     { _id:"3",nom: "Johan", email:"johan@gmail.com" }
   ]
)

db.operateur.drop()
db.operateur.insert(
   [
     { _id: 1,nom: "Telma", prefixe:"034" },
     { _id: 2,nom: "Orange", prefixe:"032" },
     { _id: 3,nom: "Airtel", prefixe:"033"}
   ]
)





db.counters.insert({
	"_id":"productid",
	"sequence_value": 0
})
function getNextSequenceValue(sequenceName){
   var sequenceDocument = db.counters.findAndModify({
      query:{_id: sequenceName },
      update: {$inc:{sequence_value:1}},
      new:true
   });
   return sequenceDocument.sequence_value;
}

db.products.insert({
   "_id":getNextSequenceValue("productid"),
   "product_name":"Apple iPhone",
   "category":"mobiles"
})
db.products.insert({
   "_id":getNextSequenceValue("productid"),
   "product_name":"Samsung S3",
   "category":"msrrrr"
})

db.products.find({})

db.counters.insert({
	"_id":"personneid",
	"sequence_value": 0
})
db.personne.insert({
   "_id":getNextSequenceValue("personneid"),
   "name":"Iavo"
})
db.personne.insert({
   "_id":getNextSequenceValue("personneid"),
   "name":"Tianaaaaaaaaaaaa"
})
db.personne.find({})
db.client.find({})