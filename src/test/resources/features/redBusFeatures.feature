Feature: Search buses between two cities

Scenario: Go to RedBus and search buses

Given User is on RedBus homePage

When I enter source city and destination city
	| sourcecity | destcity |
	| Nagpur     | Surat    |
	| Mumbai     | Pune     |

Then Search Results are correct 

