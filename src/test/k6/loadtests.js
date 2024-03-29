import http from "k6/http";
import { sleep } from "k6";

const postParams = {
  headers: {
    "Content-Type": "application/x-www-form-urlencoded",
  },
};

export const options = {
  scenarios: {
    get_homepage_scenario: {
      exec: "getHomepage", // the function "getHomepage" is called in this scenario
      vus: 10, // we have 10 virtual users
      executor: "per-vu-iterations", // every virtual user (VU) calls getHomepage equally often
      iterations: 10, // namely 10 times
    },
    add_todo_scenario: {
      exec: "addTodo", 
      vus: 10, 
      executor: "per-vu-iterations", 
      iterations: 10, 
    },
    check_palindrome_scenario: {
      exec: "checkPalindrome", 
      vus: 10, 
      executor: "per-vu-iterations", 
      iterations: 10, 
    },
  },
};

export function getHomepage() {
  http.get(`${__ENV.BASEURL}`); // use backtick ` quotes to resolve env var!
}

export function addTodo() {
  const url = `${__ENV.BASEURL}/todolist`;

  // open the web page
  http.get(url);

  // user thinks and types for one second
  sleep(1);

  // submit new todo
  const playload = {
    description: Math.random(),
    priority: Math.round(Math.random() * 2) + 1,
  };
  http.post(url, playload, postParams);

  // open the web page again
  http.get(url);
}

export function checkPalindrome() {

  // TODO implement this scenario exec function
  // The only post parameter is called "palindromecandidate"
  const url = `${__ENV.BASEURL}/palindrome`;

  // open the web page
  http.get(url);

  // generate a random string
  sleep(2);

  const randomString = Math.random().toString(36).substring(2, 10);

  // send a POST request with the random string as the palindrome candidate
  const payload = {
    palindromecandidate: randomString,
  };
  http.post(url, payload, postParams);

  // open the web page again  
  http.get(url);
}
