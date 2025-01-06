# Test Automation Engineering Bootcamp - Project 2

### Step 1
- Create repository Project2
- Create branch project_dev and make following changes in this branch

### Step 2
Create class OfferTests in swoop package:
For each test in this homework define Severity & Priority
1) searchTest:
- Navigate to https://swoop.ge
- Perform a search with valid keywords.
- Validate that results match the query.
- Search with invalid or gibberish keywords.
- Ensure a "No Results Found" message appears.
  Note: Use DataProvider for search inputs

2) paginationTest:
- Navigate to https://swoop.ge
- Go to "კატეგორიები".
- Hover on any category and choose any sub-category.
- Navigate to the second and third pages of results.
- Validate that results differ from the first page and match filters.
- Click "Next" and "Previous" buttons and ensure navigation is smooth.

3) offerLocationTest:
- Navigate to https://swoop.ge
- Go to "კატეგორიები"
- Hover on any category and choose any sub-category.
- Navigate to the first returned item and click to Location.
  - Validate that window has scrolled to the Map.

4) numberOfGuestsTest:
- Navigate to https://swoop.ge
- Go to any Eat&Drink.
- Filter with 'Number of Guests'.
- Validate with the offer description that the filter worked correctly.

5) changeLanguageTest:
- Navigate to https://swoop.ge
- Switch between English and Georgian.
- Validate that UI text and labels update correctly.

### Step 3
- Use the provided SQL script to create a table in your database.
- Go to saucedemo.com, look at the list of users on the login page and insert those users into your database using Aqua's console.

Create class LoginTests in saucedemo package:
1) successfulLoginTest:
- Go to https://saucedemo.com
- Select standard_user credentials from your database.
- Login with this user.
- Validate that all images on the landing page are loaded.

2) bannedUserLoginTest:
- Go to https://saucedemo.com
- Select locked_out_user from your database.
- Login with this user.
- Validate that 'Epic sadface: Sorry, this user has been locked out.' message appears.
- Validate that the red X icon also is visible.

3) logOutTest:
- Go to https://saucedemo.com
- Select standard_user from your database.
- Login with this user.
- Log out.
- Validate that Username and Password inputs are empty.


### Step 4
- Create testng.xml
- Create two groups with name SwoopRegression and SauceDemoLogin
- Include all Swoop tests in SwoopRegression and all SauceDemo tests in SauceDemoLogin

### Step 5
- Create a conflict in 4 files.
- Resolve the conflict on Github and only then make a pull request on main. (provide screenshots)
- Add mentors as reviewers

### Project Development Requirements

- Student HAS TO use Page Object Model and Fluent Interface patterns (also correct package structure).
- Student HAS TO use to run groups in parallel.
- Student HAS TO use before and after methods for opening and closing browser.
- Student HAS TO use soft assertion if there is more than one assert in the test (does not apply to Selenide assertions).
- Student HAS TO provide step name, test name and description in report.
- Student HAS TO use Allure and group tests with Epics, Features and Stories.
- Student HAS TO generate readable Allure report and attach it to the classroom post.
- Student HAS TO add screenshots for failed test cases.
- Student SHOULD NOT use Thread.sleep() or Selenide sleep().

### Bonus Challenges

- Option1: Implement BrowserStack/Sauce Labs integration for cross-browser testing.
- Option2: Implement Visual or Accessibility Test.
- Option3: Design a REST API test suite for endpoints related to Swoop's offers.
- Option4: Mock external dependencies using WireMock or similar tools.

### Grading
- The project is worth **400** points. The minimum grade is 150.
- Earn 100 bonus points by completing one of the bonus challenge options.

About our project development requirements:
- Everything met: full grade.
- At least one not met: -20% from the grade.
- 3 or more not met: 0%.