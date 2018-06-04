# Data-Analysis-Application
The project developed is a Data Analysis Application for Yelp.com’s business review data. 
The emphasis being inclined towards the database infrastructure of the application. 

In 2013, Yelp.com has announced the “Yelp Dataset Challenge” and invited students to use this data in an innovative way and 
break ground in research. In this project I query this dataset to extract useful information for local businesses and individual users. 

The Yelp data is available in JSON format. The original Yelp dataset includes 42,153 businesses, 252,898 users, and 1,125,458 reviews 
from Phoenix (AZ), Las Vegas (NV), Madison (WI) in United States and Waterloo (ON) and Edinburgh (ON) in Canada. 
(http://www.yelp.com/dataset_challenge/). In this project I used a smaller and simplified dataset. 
This simplified dataset includes only 20,544 businesses, the reviews that are written for those businesses only, and the users 
that wrote those reviews.

I developed a target application which runs queries on the Yelp data and extracts useful information. The primary users for this application will be potential customers seeking for businesses and users that match their search criteria. My application have a user interface that provides the user the available business categories (main, sub-categories), the attributes associated with each business category along with business review and yelp user information associated with each business category. Using this application the user will search for the businesses from various business categories that have the properties (attributes) the user is looking for. 

Faceted search has become a popular technique in commercial search applications, particularly for online retailers and libraries. It is a technique for accessing information organized according to a faceted classification system, allowing users to explore a collection of information by applying multiple filters. Faceted search is the dynamic clustering of items or search results into categories that let users drill into search results (or even skip searching entirely) by any value in any field. Users can then “drill down” by applying specific constraints to the search results.

In this application, the user can filter the search results using available business attributes (i.e. facets) such as category, sub-category, attributes, reviews, stars and votes. Each time the user clicks on a facet value; the set of results is reduced to only the items that have that value. Additional clicks continue to narrow down the search— the previous facet values are remembered and applied again.  

I have designed my application a standalone Java application. 

References: 
  1. Yelp Dataset Challenge, http://www.yelp.com/dataset_challenge/ 
  2. Samples for users of the Yelp Academic Database, https://github.com/Yelp/dataset-examples 
