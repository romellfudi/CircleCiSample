# CircleSample on Android Projects

[![platform](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html) 
[![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=17) 
[![](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/romellfudi/CircleCiSample/blob/master/LICENSE) 
[![]( https://img.shields.io/circleci/project/github/romellfudi/CircleCiSample.svg)](https://circleci.com/gh/romellfudi/CircleCiSample/tree/master)

#### Author Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

Use CircleCi Service, please create acount and register on CircleCi:

[![image](snapshot/circleci.png#circleci)](https://circleci.com/)

## Basic Module

When your Android/Gradle project its ready, need  register application on CircleCi. 

In this tutorial we use Github Repository, CircleCi need a *yml file* to connect with gradle whole put on  '.circleci/':

> ./circleci/config.yml

```yml
version: 2
jobs:
  build:
    working_directory: ~/code
    docker:
      - image: circleci/android:api-25-alpha
    environment:
      JVM_OPTS: -Xmx3200m
    steps:
      - checkout
      - restore_cache:
          key: jars-{{ checksum "build.gradle" }}-{{ checksum  "app/build.gradle" }}
      - run:
         name: Chmod permissions #if permission for Gradlew Dependencies fail, use this.
         command: sudo chmod +x ./gradlew
      - run:
          name: Download Dependencies
          command: ./gradlew androidDependencies
      - save_cache:
          paths:
            - ~/.gradle
          key: jars-{{ checksum "build.gradle" }}-{{ checksum  "app/build.gradle" }}
      - run:
          name: Run Tests
          command: ./gradlew lint test
      - store_artifacts:
          path: app/build/reports
          destination: reports
      - store_test_results:
          path: app/build/test-results

```

![image](snapshot/A.png#vertical)

In the configuration of the Github, must be insured that the permissions of writing are enabled for committing immediate in the original branch

![image](snapshot/B.png#center)

For dynamic, create a test unit that throw error assert on Java Class into test directory:

![image](snapshot/AS.png#center)

Write a error comment into commit, just only for visualize on Repository and CircleCi

![image](snapshot/AT.png#center)

Create and Push a new branch with this commit

![image](snapshot/AT_U.png#center)

When pushed on remote repository, we can view on CircleCi dashboard, on this display show every testing, the status change on real time `running`

When finished, display one of two status failed or success. In this case show an issue

![image](snapshot/AU.png#center)

Suppose we found the problem, test unit cases had been corroborated, make commit and push it, into CircleCi dashboard show automatically

![image](snapshot/AV.png#center)

![image](snapshot/AX.png#center)

Great CircleCi run all test cases and not find any issue, but found a previous test case failed already corrected it.

![image](snapshot/AY.png#center)

Summarizing the dashboard of circleCi, shows the following:

* NOT RUN: Had a test case dont run, circleCi need permissions for run testing cases.
* SUCCESS: All test cases run successful
* FAILED: Excited test cases don't run well
* FIXED: Previous test case failed already had corrected it

![image](snapshot/AZ.png#center)

## Intermediate Module

Once test the potential of the tool we are going to see the really useful thing that we can do with Circleci, let's suppose that in our team development, then we want to assure the main branch of our repository always have a stable version, where everything developed has been proved correctly.

On Github go to configure and edit branch permissions, stay blank space in 'write access' input, and uncheck or revoke all permissions for revoking directly new changes, and enable pull request task for everybody.

![image](snapshot/C.png#center)

Create a new testing case call 'serviceModule', in my case use mockito library 

![image](snapshot/D.png#center)

Make the respective commit

![image](snapshot/E.png#center)

But tried to send our changes to the remote 'origin', the repository will reject, because dont have permissions

**Now, What we do?**

![image](snapshot/EA.png#center)

**Simple, create a new branch from our worked branch**
If use Android Studio, on 'Push Commits' window, write the name for new branch called 'BranchServiceTest'

![image](snapshot/F.png#center)

![image](snapshot/G.png#center)

When pushed the branch, normally work because we only disabled the changes on master branch or directly push

![image](snapshot/H.png#center)

On the CircleCi dashboard, a new record with **NOT RUN** label and the last commit, CircleCi detected new changes on repository but doesn't had permissions.

![image](snapshot/I.png#center)

On Github we look the three change had a new `test_branch` branch, builed from AndroidStudio

![image](snapshot/J.png#center)

Suppose a person in charge of the changes, witch create the new branch need merge on remote origin, the person create a pull request

![image](snapshot/L.png#center)

On details put a message for the respective merge request and indicate close branch when resolved conclits.

![image](snapshot/M.png#center)

On our project Pull Request display a new request:

![image](snapshot/MN.png#center)

![image](snapshot/N.png#center)

On CircleCi dashboard, added a new record,  when run all test and passed. The record status will change. In our case 'success'

![image](snapshot/P.png#center)

On the screen display we visualize a counter of executed tests, in our case not detected that the issues of other test cases have been modified, only it will execute our test 'serviceModule' of the class ModuleTest

![image](snapshot/Q.png#center)

When the administrador had confirm the new implementation, procede to merge. **Assuring a rate of reliability of the code at all time** 

![image](snapshot/R.png#center)

Since we can estimate the tool Circleci, it allows us a great usefulness, for the management and control of the versioning of the cases of tests (proofs) automated, allowing to diversify the tasks between the collaborators of the project where they need test

## Advance module

On CircleCi dashboard show the result:

![image](snapshot/T.png#center)

We can see the record to detail of the testing, step by step display all the commands that we had written on yml file:

![image](snapshot/U.png#center)

On CircleCi yml file, check 'Chmod permissions'. Because it is very usual that the beginners in this tool have problems due to depending on the this machine(CircleCi Server)  would need permissions

```yml
...
jobs: ...
  build: ...
    steps: ...
      - run: ...
         command: sudo chmod +x ./gradlew
...
```

CircleCi tools supported the gradle flavors, in this project use 'SonarQube' flavor, for connect it to a base of facts of testing need it on the part of the organization:

![image](snapshot/V.png#center)

**2018, July**


<style>
img[src*='#center'] { 
    width:500px;
    display: block;
    margin: auto;
}
img[src*='#vertical'] { 
    width:250px;
    display: block;
    margin: auto;
}
img[src*='#circleci'] { 
    width:100px;
    display: block;
    margin: auto;
}
</style>
