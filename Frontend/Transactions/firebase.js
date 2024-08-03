// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.0.0/firebase-app.js";
import { getStorage, ref, uploadBytes, getDownloadURL } from "https://www.gstatic.com/firebasejs/9.0.0/firebase-storage.js";


const firebaseConfig = {
    apiKey: "AIzaSyApJIGRF8GTOLLehr2VL1y4SVXHCaL-m0Q",
    authDomain: "online-cpr.firebaseapp.com",
    databaseURL: "https://online-cpr-default-rtdb.firebaseio.com",
    projectId: "online-cpr",
    storageBucket: "online-cpr.appspot.com",
    messagingSenderId: "68642567051",
    appId: "1:68642567051:web:1b9cebf666ffaad331358e",
    measurementId: "G-4W97B0191X"
  };

const app = initializeApp(firebaseConfig);
const storage = getStorage(app);

export { storage };
