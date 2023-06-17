# Brightskies_task
You will have to build an app that displays a list of recipes. Each recipe from this list
should be clickable. When clicked, the recipe should open a details page with all
relevant information.

## ScreenShots:
<p align="center">
  <img src="img/1.png" height="450" width="220">
  <img src="img/2.png" height="450" width="220">
  <img src="img/3.png" height="450" width="220">
  <img src="img/4.png" height="450" width="220">
</p>

## Demo
<p>
  <img src="img/demo.gif" height="500" width="250">
</p>


## APK Download
[Click here](https://drive.google.com/file/d/1MgINknWzpHDKiSAVB8G5enQi5mQDj6s7/view?usp=sharing)


## App-Arch
<p>
  <img src="img/app_arch.png" height="250" width="250">
</p>

- base: contains base code
- common: contains common classes that may used at many places like (composable fun)
- database: room and datastore
- modules: contains app features (login - recipes - user ..etc)
- util: for utility classes

## In-App architecture
<p>
  <img src="img/arch.png" height="200" width="900">
</p>

## In-App Testing
<p>
  <img src="img/testing.png" height="220" width="300">
</p>

## Credentials:
Email: ahmed@gmail.com   
Password: 123456   


## Unit testing for:
<p>
  <img src="img/unit_test.png" height="250" width="250">
</p>

- Login module (data - repository - useCase)  
- User module (data - repository - useCase)   
- Recipes module (data - repository - useCase)   
- Validation.kt   

## UI test for:
<p>
  <img src="img/ui_test.png" height="250" width="250">
</p>

`Hint: Run this class HomeActivityTest -> when user is not logged in the app `     
`Hint: Run this class RecipesScreenKtTest -> when user is logged in the app `

- All app flow   
- Login Screen   
- Recipes Screen   


## Features
- Obfuscation
- Simple UI
- Simple Animations
- Config changes handling

## Tools & APIs
- Jetpack Compose
- Compose navigation
- Clean architecture
- MVVM arch pattern (reactive MVI)
- Coroutines
- Channels & flows
- Retrofit
- Room
- Coil
- Unit testing
- UI testing
- Proguard


## Resources
- Testing
    - [Testing cheatsheet](https://developer.android.com/jetpack/compose/testing-cheatsheet)
    - [Testing your Compose layout](https://developer.android.com/jetpack/compose/testing#assertions)
    - [Write automated tests with UI Automator](https://developer.android.com/training/testing/other-components/ui-automator)
    - [Testing in Jetpack Compose - codelab](https://developer.android.com/codelabs/jetpack-compose-testing)
    - [Hilt testing guide](https://developer.android.com/training/dependency-injection/hilt-testing#groovy)

