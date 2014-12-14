# FOODit Test

Author: Abraham Marin-Perez

GENERAL NOTES:

Logic has been moved as much as possible to "entity" classes, this are available in the package c.f.t.s.entities. For this reason, controllers have little to no logic, and act mostly as glue-logic to join the entities and the MVC framework.

Entities, are unit tested, controllers aren't. The reason for this is testing controllers automatically is cumbersome (heavy mocking and/or data setup is required), while the gains are negligible (logic is tested in entities). Manual test of the overall server behaviour has been performed. 

Create the set of REST API's that can answer the following questions:

  1.	Total number of orders for each restaurant
  
  This is available at http://<server>/restaurant/{restaurant}/orders/total
  
  2.	Total amount (money) of sales per restaurant
  
  This is available at http://<server>/restaurant/{restaurant}/sales/total
  
  3.	The most frequently ordered meals on FOODit Platform
  4.	The most frequently ordered category for each restaurant