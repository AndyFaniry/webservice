https://fr.blog.businessdecision.com/tutoriel-mongodb-requetes/#:~:text=MongoDB%20est%20une%20base%20de,disponibilit%C3%A9%2C%20tol%C3%A9rance%20aux%20pannes).

mongod
mongo

db.client.insert(
   [
     { _id:"1",nom: "Iavotiana", email:"iavotiana@gmail.com" },
     { _id:"2",nom: "Faniry", email:"faniry@gmail.com" },
     { _id:"3",nom: "Johan", email:"johan@gmail.com" }
   ]
)

db.client.find({})