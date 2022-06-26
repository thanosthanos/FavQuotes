# FavQuotes
This is an application that shows the user public quotes, user can search quotes through a search term, see the details of the quote by tapping on it and supports
Sign in and Sign up functionality.

# Installation
Clone this repository and import into Android Studio (version used Chipmunk 2021.2.1)

# Arhitecture and libraries
For the purpose of this project the MVVM architecture was used with various other libraties such as Retrofit, Hilt, Coroutines and JetPack Compose for the UI layer.

# App Design
The app shows a random quote when the user opens the app and the user can navigate to one of the following screens:

1- Public quotes: The user can see a list of public quotes with the quote description, author and number of favorites. If the user taps on a quote, more details are 
visible such as the tags and number of upvotes/downvotes for this quote.

2- Search quotes: The user can search for quotes with a search term and if any quotes matches the term, a list of quotes will be presented to the user.

3- Login: The user can login in the app by using the username or email and the password.

4- Sign up: The user can sign up with a username, an email and a password.
