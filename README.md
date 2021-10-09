# LearCard
Projektarbeit: Lernkartei (für die htw saar)

Application for learning with cards.

Backend:
-Spring Boot
-Hibernate
-MySQL

Frontend:
-React
-Material UI

To start:
1. Create database (MySQL): "learcard"
2. Open terminal in api folder and run commands: 
- mvn clean install
- mvn spring-boot:run
3. Open terminal in app folder and run commands:
- npm install
- npm run start


What is possible?
- Register / Login
- Add Group / delete Group
- Add Card to Group / delete Card from Group
- Learn Session -> 
Logic: In Level 0 all cards from group are visible, in next level only cards that were answered wrong and so on.
When all cards are answered right then session is finished. It is possible to finish session at any time (reset).
