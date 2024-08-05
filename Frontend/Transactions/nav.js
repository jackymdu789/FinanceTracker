// navbar.js

const navbarHTML = `
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="padding: 20px 30px;">
  <a class="navbar-brand" href="index.html">Finance Tracker</a>
  <button
    class="navbar-toggler"
    type="button"
    data-bs-toggle="collapse"
    data-bs-target="#navbarNav"
    aria-controls="navbarNav"
    aria-expanded="false"
    aria-label="Toggle navigation"
  >
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav" style="flex-direction: row-reverse;">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item">
        <a class="nav-link" href="index.html#dashboard">Dashboard</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="index.html#transactions">Transactions</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="profile.html">Profile</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="bill.html">Bill</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" onclick="handleClose()">Logout</a>
      </li>
    </ul>
  </div>
</nav>
`;

function addNavbar() {
  const navElement = document.getElementById('nav');
  if (navElement) {
    navElement.innerHTML = navbarHTML;
  }
}

const handleClose = () => {
  sessionStorage.clear();
  window.location.reload();
};

document.addEventListener('DOMContentLoaded', addNavbar);
