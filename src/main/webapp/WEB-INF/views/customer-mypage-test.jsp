<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FoodieTree for 소비자</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            display: flex;
        }
        .profile, .info {
            border: 1px solid #ccc;
            padding: 10px;
            margin: 10px;
        }
        .profile {
            width: 30%;
        }
        .info {
            width: 65%;
        }
        .profile img {
            width: 100px;
            height: 100px;
            border-radius: 50%;
        }
        .reservation-list {
            border-top: 1px solid #ccc;
            padding-top: 10px;
        }
        .reservation-item {
            display: flex;
            justify-content: space-between;
            border: 1px solid #ccc;
            margin: 5px 0;
            padding: 10px;
        }
        .stats {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }
        .stats div {
            border: 1px solid #ccc;
            padding: 20px;
            width: 45%;
        }
    </style>
</head>
<body>
<form action="/customer/mypage-main" id="customer-mypage-main" method="post">
    <div class="container">
        <div class="profile">
            <img src="path/to/default/profile/image.jpg" alt="Customer profile image">
            <h3><%= request.getAttribute("username") %></h3>
            <p><%= request.getAttribute("email") %></p>
            <h4>마이페이지</h4>
            <p>개인정보수정</p>
        </div>
        <div class="info">
            <h3>예약 내역</h3>
            <div class="reservation-list">
                <c:forEach var="reservation" items="${reservations}">
                    <div class="reservation-item">
                        <span>${reservation.storeName}</span>
                        <span>${reservation.status}</span>
                        <span>${reservation.pickupTime}</span>
                    </div>
                </c:forEach>
            </div>
            <div class="stats">
                <div>10kg의 음쓰를 줄였습니다</div>
                <div>지금까지 10만원을 아꼈어요</div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
