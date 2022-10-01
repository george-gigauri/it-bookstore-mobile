# IT Book Store Mobile
Find your perfect IT book
## Design
![THIS IS AN IMAGE](https://github.com/george-gigauri/it-bookstore-mobile/blob/main/ITBookStore%20Design.jpg?raw=true)

Application consists of three main pages: Home (Where newest books are displayed), Search and Wishlist.
Search page can be reached through two different ways: 

1. From home page's top left loop icon
2. From bottom navigation view

By clicking on the book item, you will be navigated to book details page, where you can add book to wishlist or download as PDF.

## Technologies used

Loading images to ImageView, I used Coil library.

Working with lists with **RecyclerView**, navigating between different fragments using **NavigationComponent** and passing arguments between them using **SafeArgs.**
For networking, **Retrofit and Gson libraries** are consumed. **Room Database** for saving books into local storage. 

On Debug variant, you can see notification sent by the app, it actually logs HTTP requests so you can see networking details and I used **ChuckerInterceptor** for that.

Splash screen is shown while home screen is loading and disappears when loaded successfully.

**Dagger hilt** simplifies dependency injection job.

**Kotlin Coroutines** helps switching between main and background threads and also takes less writing to execute async tasks.
