# Project 2 - *NYTimesApp*

**NYTimesApp** is an android app that allows a user to search for articles on web using simple filters. The app utilizes [New York Times Search API](http://developer.nytimes.com/docs/read/article_search_api_v2).

Time spent: **35** hours spent in total

## User Stories

The following **required** functionality is completed:

* [YES] User can **search for news article** by specifying a query and launching a search. Search displays a grid of image results from the New York Times Search API.
* [YES] User can **scroll down to see more articles**. The maximum number of articles is limited by the API search.
* [YES] User can tap on any image in results to see the full text of article **full-screen**

The following **optional** features are implemented:

* [YES] Used the **ActionBar SearchView** or custom layout as the query box
* [YES] User can **share an article link** to their friends or email it to themselves
* [YES] Improved the user interface and experiment with image assets and/or styling and coloring
* [  ] User can click on "settings" which allows selection of **advanced search options** to filter results
  * [ ] User can configure advanced search filters such as:
    * [YES] Begin Date (using a date picker)
    * [YES] News desk values (Arts, Fashion & Style, Sports)
    * [YES] Sort order (oldest or newest)
  * [YES] Subsequent searches have any selected filters applied to the results
  * [ ] Uses a lightweight modal dialog for filters rather than an activity
* [YES] Replaces the default ActionBar with a [Toolbar](http://guides.codepath.com/android/Using-the-App-ToolBar).
* [ ] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce view boilerplate.
* [ ] Replace `GridView` with the [RecyclerView](http://guides.codepath.com/android/Using-the-RecyclerView) and the `StaggeredGridLayoutManager` to improve the grid of image results displayed.
* [YES] Use Parcelable instead of Serializable leveraging the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).
* [ ] Replace Picasso with [Glide](http://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en) for more efficient image rendering.
* [ ] Before an article search is triggered by the user, displays the current top stories of the day by default.
* [ ] Hides the `Toolbar` at the top as the user scrolls down through the results using the [CoordinatorLayout and AppBarLayout](http://guides.codepath.com/android/Using-the-App-ToolBar#reacting-to-scroll).
* [ ] Leverage the popular [GSON library](http://guides.codepath.com/android/Using-Android-Async-Http-Client#decoding-with-gson-library) to streamline the parsing of JSON data and avoid manual parsing.

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app:
I was pretty ahead on the app the third day working on it (already working on filters) but I spent almost all day debugging why 
my infinite scrolling wasn't working. It turned out that the API had a limit for callbacks. I figured this out by discussing it 
with my peers. The next day, Thursday, I spent the whole day pretty much debugging my filter because of a few mistakes (I need to get better at debugging).
Today, Friday, I finally got my filter working but it took a while again because of the debugging process. 

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright [2016] [Luis Gutierrez]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
