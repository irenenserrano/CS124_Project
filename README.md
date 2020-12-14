# CS124_Project
Final Project for CS 124 - Data Structures and Algorithms

What functionality is working sofar? 
(12/10)insert, searchAllContacts, printAllContacts, and size are passing all tests. Tests for delete and find to be written later, once implementations are ready.
(12/13) delete and find are passing they're tests.

What problems/bugs are you currently running into(if any)?
(12/10)none to report on for the completed implementations
(12/13)Trying to incorporate tests for delete turned out to be difficult; there are some issues with using a global ContactList object. To go into detail, before incorporating the tests for delete, all insertions were made in order and the tests for searchAllContacts and printAllContacts would only register the contacts that were inserted within their ArgumentsProviders. But when trying to incorporate delete actions in earlier tests, the same tests would register ALL the insertions and deletes at once rather than in order. Due to this issue, I needed to create a separate local ContactList object for each of the delete tests and the test for find(I found that the test for this implementation would not register the existing contacts within the global ContactList object). And to give explanation on why I have a global level ContactList object, I wanted to write a test that would test both the insert and rehash method. 

Provide a planned schedule for when you aim to complete each remaining aspect of the project.
(12/10)plan is to have all tests for all implementations written by mid-day Friday and then missing implementations debugged by end-of-day Friday.(12/14) Had to create some last-minute tests using main rather than JUnit.

