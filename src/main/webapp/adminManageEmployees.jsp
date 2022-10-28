<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Manage Employees</title>
        <!-- link file CSS -->
        <link rel="stylesheet" href="./css/adminHomeStyle.css">
        <!-- BoxIcon CDN Link -->
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>

    <body>
        <div class="header">
            <div class="header-menu">
                <!-- even menu sidebar -->
                <div class="sidebar-button__logo">
                    <div class="sidebar-button">
                        <i class='bx bx-menu sidebarBtn'></i>
                    </div>
                    <!-- div of logo -->
                    <div class="logo-details">
                        <img src="./images/logo.jpg" alt="Logo Phuong Nam Bookstore">
                        <span class="logo_name">Book Store</span>
                    </div>
                </div>
                <div class="seachbox-profile">
                    <div class="wraper_searchbox_profile">
                        <div class="search-box">
                            <input type="text" placeholder="Search...">
                            <button type='submit'><i class='bx bx-search-alt-2'></i></button>
                        </div>
                        <div class="profile_details">
                            <img src="images/admin1.png" alt="image admin">
                            <div class="name_role">
                                <span class="name">${role}</span>
                                <i class='bx bxs-chevron-down'></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="wrapper-body">
            <div class="sidebar">
                <!-- link in sidebar -->
                <ul class="nav-links">
                    <li>
                        <c:choose>
                            <c:when test="${role=='ADMIN'}">
                                <a href="./adminDashBoard.jsp">
                                    <i class='bx bx-grid-alt'></i>
                                    <span class="links_name">Dashboard</span>
                                </a>
                            </c:when>
                            <c:when test="${role=='STAFF'}">
                                <a href="staffNodifyPage">
                                    <i class='bx bxs-bell'></i>
                                    <span class="links_name">Nodify</span>
                                </a>
                            </c:when>
                        </c:choose>
                    </li>
                    <li>
                        <c:choose>
                            <c:when test="${role=='ADMIN'}">
                                <a href="./adminManageEmployees.jsp">
                                    <i class='bx bx-user'></i>
                                    <span class="links_name">Employees</span>
                                </a>
                            </c:when>
                            <c:when test="${role=='STAFF'}">
                                <a href="./adminManageEmployees.jsp">
                                    <i class='bx bx-notification'></i>
                                    <span class="links_name">Request</span>
                                </a>
                            </c:when>
                        </c:choose>
                    </li>
                    <li>
                        <c:choose>
                            <c:when test="${role=='ADMIN'}">
                                <a href="#">
                                    <i class='bx bx-home'></i>
                                    <span class="links_name">Overview</span>
                                </a>
                            </c:when>
                            <c:when test="${role=='STAFF'}">
                                <a href="#">
                                    <i class='bx bx-add-to-queue'></i>
                                    <span class="links_name">Importation</span>
                                </a>
                            </c:when>
                        </c:choose>

                    </li>
                    <li>

                        <a type="submit" href="bookAction?action=bookPage1">
                            <i class='bx bx-book'></i>
                            <span class="links_name">Books</span>
                        </a>
                    </li>
                    <li>
                        <c:choose>
                            <c:when test="${role=='ADMIN'}">
                                <a href="#">
                                    <i class='bx bx-trending-up'></i>
                                    <span class="links_name">Statistic</span>
                                </a>
                            </c:when>
                            <c:when test="${role=='STAFF'}">
                                <a href="inventoryAction">
                                    <i class='bx bx-carousel' ></i>
                                    <span class="links_name">Inventory</span>
                                </a>
                            </c:when>
                        </c:choose>
                    </li>
                    <li>
                        <a href="login.jsp">
                            <i class='bx bx-log-out'></i>
                            <span class="links_name">Log out</span>
                        </a>
                    </li>
                </ul>
            </div>

            <!-- home content -->
            <div class="home-section">
                <!-- home-content -->
                <div class="home-content">

                    <div class="overview-boxes">

                        
                    </div>
                </div>
            </div>
        </div>
        
        <script src="./js/script.js"></script>

    </body>

</html>