# Design Document

## _Collector's Stash_ Design

## 1. Problem Statement
Many individuals have a passion for collecting various and unique items, be it comic books, stamps, or other collectibles. They often resort to carrying physical notepads and books to manually document and keep track of their collections. This traditional method is overwhelming and can also be prone to errors, loss, and inefficiency. To address this issue, I am developing a innovative application aimed at providing collectors with a digital solution for managing and preserving their collections.

## Top Questions to Resolve in Review
1. How many tables do I need?
2. Do I need to use a GSI (Let's say I use it so they can filter/search for their favorites or such)
3. What is the main goal vs the stretch goals, but clear on what the end goal looks like realistically

## Use Cases
U1. _As a user, I would like to store my collectibles_
u2. _As a user, I would like to be able to view the books in my collection_
u3. _As a user, I would like to favorite the books I like most_
U4. _As a user, I would like to view to store the price I paid for such book_
U5. _As a user, I would like to select a series I am collection and see the missing books_
U6. _As a user, I would like to update my collection with new books or collectibles I have purchased_
U7. _As a user, I would like to update my collection, to get rid of books I might have sold off and no longer own_
U8. _As a user, I would like add a book to a new series I have started collection_
U9. _As a user, I would like create an account_
U10. _As a user, I would like to login to my account_
U12. _As a user, I would like to see collection based of my login_

Stretch goals:
- Search Option
- Adding Images to the comics added(if wanted, user takes a picture and able to store that image)
- Better design on website

## Project Scope
- view books that have been added to my collection
- add books to a new series I am collection
- view all books in my collection

## Proposed Architecture Overview
We will use API Gateway and Lambda to create endpoints()
(GetAllComicBooksLambda, GetAllFavoriteLambda, GetAllStampsLambda, GetAllCoinsLambda, AddComicBookToCollectionLambda, AddStampsToCollectionLambda, AddCoinsToCollectionLambda, UpdateComicBookCollectionLambda, UpdateStampCollectionLambda, UpdateCoinCollectionLambda, SearchCollectionLambda)
_that will handle the creation, update, and retrieval of events and vendors to satisfy our requirements_

## API

## 6.1 Public Models
```
// ComicBookModel

String id
String volumeNumber
String issueNumber
String date
Int price
Boolean isFavorite
String publisher
```
// Can these Coins and Stamp use same data table?
// If not use different tables
```
// CoinAndStampModel

String id
String year
String Condition
Int price
String Country
Boolean isFavorite
```

## 6.2 _Get All Comic Books Endpoint_

_* Accepts `GET` requests to `/comicbooks/`
* Scans comics table and returns all ComicBookModels.
    * If no ComicBookTable is found
      `ComicBookNotFoundException`_

## 6.3 _Get All Stamps Endpoint_

_* Accepts `GET` requests to `/stamps/`
* Scans stamps table and returns all CoinAndStampModel.
    * If no StampTable is found
      `StampsNotFoundException`_

## 6.4 _Get All Coins Endpoint_

_* Accepts `GET` requests to `/coins/`
* Scans coins table and returns all CoinAndStampModel.
    * If no CoinsTable is found
      `CoinsNotFoundException`_

## 6.5 _Get One ComicBook Endpoint_

_* Accepts `GET` requests to `/comicbooks/:id/volumeNumber`
* Accepts a comicbook ID and volumeNumber returns the corresponding Comic Book Title.
    * If the given comicBook ID is not found or volumeNumber is not found, will throw a
      `ComicBookNotFoundException`_

## 6.6 _Get One Stamp Endpoint_

_* Accepts `GET` requests to `/stamps/:id`
* Accepts a stamp ID and returns the corresponding Stamp Model.
    * If the given stamp ID is not found will throw a
      `StampNotFoundException`_

## 6.7 _Get One Coin Endpoint_

_* Accepts `GET` requests to `/coins/:id/`
* Accepts a coins ID and returns the corresponding Coin Model.
    * If the given coin ID is not found will throw a
      `CoinNotFoundException`_

## 6.7 _Add ComicBook Endpoint_

* Accepts `POST` requests to `/comicbook/id/volumeNumber/add`
* Accepts a event ID and a name to be added.
    * For security concerns, we will validate the provided vendor name does not
      contain invalid characters: `" ' \`
    * If the event name contains invalid characters, will throw an
      `InvalidAttributeValueException`
    * If the event can not be added to the eventTable, will throw an `UnableToAddToTableException`
  

