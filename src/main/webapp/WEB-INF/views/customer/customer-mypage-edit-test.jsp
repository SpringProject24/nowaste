<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>FoodieTree for 소비자</title>
    <style>
        #food-img {
            width: 150px;
            height: 150px;
            border-radius: 20%;
        }

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
        .reservation-list, .issue-list {
            border-top: 1px solid #ccc;
            padding-top: 10px;
        }
        .reservation-item, .issue-item {
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
        .delete-btn {
            cursor: pointer;
            color: red;
        }
    </style>
</head>
<body>
<form action="/customer/mypage-main" id="customer-mypage-main" method="post">
    <div class="container">
        <div class="profile">
            <img src="${customerMyPageDto.profileImage}" alt="Customer profile image">
            <p class="edit-btn" onclick="editField('profileImage')">✏️</p>
            <label for="nickname"></label>
            <input id="nickname" placeholder="${customerMyPageDto.nickname}"></input>
            <span class="edit-submit-btn" onclick="fetchUpdates('nickname', document.getElementById('nickname').value)">️✅</span>
            <p class="edit-btn">✏️</p>
            <p>${customerMyPageDto.customerId}</p>
            <label for="customerPhoneNumber"></label>
            <input id="customerPhoneNumber" placeholder="${customerMyPageDto.customerPhoneNumber}"></input>
            <span class="edit-submit-btn" onclick="fetchUpdates('customer_phone_number', document.getElementById('customerPhoneNumber').value)">️✅</span>
            <p class="edit-btn">✏️</p>
            <button id="reset-pw-btn">비밀번호 재설정</button>
            <h4>마이페이지</h4>
            <p id="nickname-status"></p> <!-- Status message for nickname validation -->
        </div>
        <div class="info">
            <h4>선호 지역</h4>
            <ul id="preferredArea">
                <c:forEach var="area" items="${customerMyPageDto.preferredArea}">
                    <li onclick="deleteItem('preferredArea', '${area}')">${area} <span class="delete-btn">❌</span></li>
                </c:forEach>
            </ul>
            <h4>선호 음식</h4>
            <ul id="preferredFood">
                <c:forEach var="food" items="${customerMyPageDto.preferredFood}">
                    <li onclick="deleteItem('preferredFood', '${food.preferredFood}')">
                        <img id="food-img" src="${food.foodImage}" alt="선호음식이미지">
                        <span>${food.preferredFood}</span>
                        <span class="delete-btn">️❌</span>
                    </li>
                </c:forEach>
            </ul>
            <h4>최애 가게</h4>
            <ul id="favStore">
                <c:forEach var="store" items="${customerMyPageDto.favStore}">
                    <li onclick="deleteItem('favStore', '${store.storeId}')">
                        <img src="${store.storeImg}" alt="최애가게이미지">
                        <span>${store.storeName}</span>
                        <span class="delete-btn">❤️</span>
                    </li>
                </c:forEach>
            </ul>
            <div class="stats">
                <div>10kg의 음쓰를 줄였습니다</div>
                <div>지금까지 10만원을 아꼈어요</div>
            </div>
        </div>
    </div>
</form>
<!-- 비밀번호 재설정 모달 -->
<div id="resetPasswordModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span> <!-- X 버튼 추가 -->
        <h2>비밀번호 재설정</h2>
        <p>인증번호를 받으세요.</p>
        <button id="sendVerificationCodeBtn" onclick="sendVerificationCode()">인증번호 받기</button>
        <div id="countdown"></div>
    </div>
</div>

<script>
    const BASE_URL = 'http://localhost:8083/customer';
    const customerId = 'sji4205@naver.com'; // Replace this with the actual customer ID

    let type;

    function editField(fieldId) {
        type = fieldId;
    }

    async function fetchUpdates(type, value) {
        const payload = {
            type: type,
            value: value
        };
        console.log('Updates to be sent:', payload); // Debugging line

        try {
            const response = await fetch(`\${BASE_URL}/\${customerId}/update`, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify([payload])
            });

            if (response.ok) {
                console.log('Update successful');
            } else {
                const errorText = await response.text();
                console.error('Update failed:', errorText);
            }
        } catch (error) {
            console.error('Error updating data:', error);
        }
    }

    async function deleteItem(type, value) {

        const payload = {
            type: type,
            value: value
        };

        try {
            const response = await fetch(`\${BASE_URL}/\${customerId}/delete`, {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify([payload])
            });

            if (response.ok) {
                console.log('Delete successful');
                // Remove the item from the DOM
                const listItem = document.querySelector(`[onclick="deleteItem('\${type}', '\${value}')"]`);
                if (listItem) listItem.remove();
            } else {
                const errorText = await response.text();
                console.error('Delete failed:', errorText);
            }
        } catch (error) {
            console.error('Error deleting item:', error);
        }
    }
    function handleKeyUp(event, fieldId) {
            event.preventDefault();
        if (event.key === 'Enter') {
            const element = event.target;
            const value = element.innerText;
            console.log(element);
            console.log(value);
            element.blur(); // Remove focus to trigger the update
            fetchUpdates(fieldId, value);
        }
    }


    // 비밀번호 재설정 모달 관련 함수
    function openModal() {
        document.getElementById('resetPasswordModal').style.display = 'block';
    }

    function closeModal() {
        document.getElementById('resetPasswordModal').style.display = 'none';
    }

    // X 버튼 클릭 시 모달 닫기
    document.addEventListener('DOMContentLoaded', function() {
        const closeButton = document.querySelector('.close');
        if (closeButton) {
            closeButton.addEventListener('click', closeModal);
        }

        // 모달 바깥 클릭 시 모달 닫기
        window.onclick = function(event) {
            const modal = document.getElementById('resetPasswordModal');
            if (event.target == modal) {
                closeModal();
            }
        };
    });

    async function sendVerificationCode() {
        try {
            const response = await fetch('/sendVerificationCode', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email: '${customerMyPageDto.customerId}' }) // Replace with actual email
            });

            if (response.ok) {
                openModal(); // 모달 열기
                startCountdown(300); // 5분(300초) 카운트다운 시작
            } else {
                console.error('Failed to send verification code');
            }
        } catch (error) {
            console.error('Error sending verification code:', error);
        }
    }

    function startCountdown(seconds) {
        const countdownElement = document.getElementById('countdown');
        countdownElement.textContent = `남은 시간: ${seconds}초`;

        const interval = setInterval(() => {
            seconds -= 1;
            countdownElement.textContent = `남은 시간: ${seconds}초`;

            if (seconds <= 0) {
                clearInterval(interval);
                countdownElement.textContent = '시간 초과';
                closeModal(); // 모달 닫기
            }
        }, 1000);
    }
</script>
</body>
</html>
