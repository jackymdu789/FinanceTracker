import { isAuthenticated } from './auth.js';

const protectRoute = async (path) => {
    if (!isAuthenticated()) {
        window.location.href = 'login.html'
    } else if ((await fetch(window.location.href)).ok) {
        window.location.href = path
    } else {
        window.location.href = '404.html'
    }
};


if(!isAuthenticated()){
    const currentPath = window.location.pathname.split("/");
    const nth = currentPath.length-1;
    protectRoute(currentPath[nth]);
}
