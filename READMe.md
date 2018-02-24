**Purpose**

This is a Self employed market place where the seller posts jobs with the job description , budget amount and last date for accepting bids.
And the buyer bids on the projects for a fixed price. The winner of the bid would be the buyer who bids for lowest bid amount.

How to run this project

1) clone the project
2)Navigate to the main folder and do mvn clean install
3) Run Application as spring boot app.


This is a spring boot application. Once the application is run

**Projects**

1) A new project can be created by hitting the http://localhost:8080/project/create and passing in the request as 

{
	"description": "data entry",
	"budget": "200",
	"lastDate": "2018-02-24"
}

A unique ID would be generated for each project created.

The default status of the project would be OPEN but if the lastDate is less than current Date, status is set to "CLOSED".

2) The projects can be fetched by passing in the status as the request parameter and hitting the end point like 
http://localhost:8080/project/findAllByStatus?status=open

similarly findAll and findOne by id works for the projects.

**Buyer**

1) A new Buyer can be created by hitting the http://localhost:8080/buyer/create and passing in the request as 

{
	"name": "sandeep",
	"price": "100"
}

A unique ID would be generated for each project created.

**Bid**

1) A buyer can bid the project by hitting the end point http://localhost:8080/bid/create
 and passing in the request as 
 
 {
 	"projectId": "1",
 	"buyerId": "1"
 }
 
 As soon the bids are created, the project is updated with the lowest bid amount and winner so far as shown below
 
 {
     "id": 1,
     "description": "data entry",
     "budget": 200,
     "status": "OPEN",
     "bidAmount": 100,
     "winner": 1
 } 
 
 If a new buyer bids with the bid amount less than current bid amount, the project gets updated accordingly.
 If the project's deadline exceeds today's date project gets CLOSED.
 
 **Assumptions**
 
 1. Projects are closed immediately as the lastDate exceeds today's Date
 2. http://localhost:8080/project/getById?id=2 shows the project with the least bid amount and project's status and winner so far but an assumption is made the bids are done for this project.
 
 Time to complete exercise
 
 2:45 hours after setting up the environment
 
 Difficulty: Moderate
 
 How do you feel about exercise: 10
 
 How do you feel about coding an exercise as a step in the interview process: 10
 
 What would you change in the exercise or process: Nothing
 