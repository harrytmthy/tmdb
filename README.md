# TMDB: Clean Architecture with MVI + RxJava
&nbsp;
![TMDB](https://www.themoviedb.org/assets/2/v4/logos/408x161-powered-by-rectangle-blue-10d3d41d2a0af9ebcb85f7fb62ffb6671c15ae8ea9bc82a2c6941f223143409e.png)

## Table of Contents
* [Introduction](#introduction)
* [Project Scope](#project-scope)
* [Architecture](#architecture)
* [Methodology](#methodology)
* [Technologies](#technologies)
* [References](#references)

## Introduction
&nbsp;
![splash](https://drive.google.com/uc?export=view&id=1QP2i_5GQqRjOD5edoOo3uanF2pZklxrR) &nbsp; &nbsp; ![popular](https://drive.google.com/uc?export=view&id=1XveS49-7xAGa1X0mVfgEdl53yaENCjuj) &nbsp; &nbsp; ![detail](https://drive.google.com/uc?export=view&id=1HPOkwa3aLgpDAwW99mAyVkjay_LwjJjt)

TMDB is a demo app which uses API provided by [The Movie DB](https://developers.themoviedb.org/3) to show list of movies and its detail, which fall into three categories: Popular, Top Rated, and Favorite Movies. User can switch between categories by clicking the option menu. Movie detail is accessible by clicking a poster. All Favorite features requires Login to proceed.

The code is written in Java without any slightest interoperability with Kotlin. POJO getters and setters are generated using Lombok annotation.

## Project Scope
This project aims to create an app for demo purpose, not to implement every features in TMDB. Therefore, the project scope is limited to the following features:
* **Authentication** *(version 3)*: Login & Register
* **Movie List**: Popular, Top Rated, Favorite
* **Movie Detail**: Mark Favorite, Watch Youtube Trailers

## Architecture
![architecture](https://drive.google.com/uc?export=view&id=1d5TGoJaYXxTrSTY1aCYmeLooXGw6Y0Vl)


The project is separated into three different modules: *Data*, *Domain*, and *App (Presentation)* to match Clean Architecture design. MVI acts as a micro (module level) architecture which affects only Presentation layer. The purpose is to simplify both inputs and outputs between Presenter and the view contract, and to establish a reactive - unidirectional flow. Reducer layer in MVI is not implemented as there is no need to modify the downstream data.

## Methodology
This app was developed using TDD approach, where each component was unit tested before implemented, then refactored to ensure efficiency and its cleanliness. The testing was done using *JUnit* (for single and modular unit tests) and *JaCoCo* (for project level tests). Activities and generated classes are excluded from test coverage. As the result, this project has **90% of coverage for both classes and branches**.
	
## Technologies
TMDB app was created using
* **IDE**: Android Studio 3.5.3
* **Language**: Java 1.8
* **Dependency Injenction**: Dagger 2
* **Http client**: Retrofit
* **Async**: RxJava
* **Unit Testing**: Mockito & JaCoCo

## References
* [*Android - Clean Architecture*](https://github.com/android10/Android-CleanArchitecture) by Android10.
* [*MVI — another member of the MV&ast; band*](https://proandroiddev.com/mvi-a-new-member-of-the-mv-band-6f7f0d23bc8a) by Iveta Jurčíková.
* [*Reactive Apps with Model-View-Intent*](http://hannesdorfmann.com/android/mosby3-mvi-1) by Hannes Dorfmann.
* [*Android Clean Architecture MVI Boilerplate*](https://github.com/bufferapp/android-clean-architecture-mvi-boilerplate) by Hitherejoe.
