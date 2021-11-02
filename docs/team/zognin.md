---
layout: page
title: Geng Ning's Project Portfolio Page
---

## Project: BobTheBistroBoss

### Overview

BobTheBistroBoss (BTBB) is a desktop application for home chefs to manage their orders and inventory,
optimized for use via a command line interface (CLI).

### Summary of Contributions

* **New Feature**: Implemented commands to add and delete ingredients of an order. (Pull request [#92](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/92)).
  * **What it does**: Allows the user to add and delete ingredients of an order.
  * **Justification**: Each order has a list of ingredients.
    Users may want to change one ingredient in the list without having to key in all the other ingredients again.
    These commands allow the user to control which ingredient they want to change.
  * **Highlights**: Used a different implementation from how AB3 edits a list within an entity.
    Previously, editing a list within an entity (e.g. editing a list of tags in a contact) rewrites the entire list.
    Now, users can edit individual items in the list without the hassle of rewriting everything.

* **New Feature**: Added the ability to find items that are an exact match or lie within a range. (Pull request [#85](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/85)).
  * **What it does**: Allows the user to find relevant data with a command.
  * **Justification**: Users will store a lot of data after using the app for some time.
    The find feature allows them to filter for data they need.
  * **Highlights**: Implemented two generic classes to make the find feature more flexible.
    This allows different objects to make use of the generic classes if they intend to achieve the same search behaviour,
    which reduces code duplication.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=zognin&tabRepo=AY2122S1-CS2103T-W16-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Team tasks**:
  * Made the release of `v1.2` and `v1.3` on GitHub.
  * Set up the codebase so that the team can implement new features in a consistent manner and in parallel.
    (Pull requests [#34](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/34), [#56](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/56)).
  * Maintain the issue tracker by creating issues, adding labels and closing issues.

* **Enhancements to existing features**:
  * Implemented the overall GUI such as styling and tabs. (Pull request [#66](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/66)).

* **Review/mentoring contributions**:
  * PRs reviewed: [#69](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/69),
    [#84](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/84),
    [#120](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/120),
    [#159](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/159).
  * Planned tasks and facilitated task distribution.

* **Documentation**:
  * User Guide:
    * Added some recipe features. (Pull Request [#156](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/156)).
    * Updated feature overview. (Pull Request [#233](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/233)).
  * Developer Guide:
    * Added user stories. (Pull Request [#78](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/78)).
    * Added implementation details for the switching of tabs and editing of ingredients in orders/ recipes. (Pull Request [#158](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/158)).
    * Added some manual testing instructions. (Pull Request [#239](https://github.com/AY2122S1-CS2103T-W16-2/tp/pull/239)).

* **Community**:
  * Forum responses: [#148](https://github.com/nus-cs2103-AY2122S1/forum/issues/148),
    [#238](https://github.com/nus-cs2103-AY2122S1/forum/issues/238),
    [#251](https://github.com/nus-cs2103-AY2122S1/forum/issues/251).
