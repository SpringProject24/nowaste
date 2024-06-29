// =========== 전역 변수 ==============
const customerId = "test@gmail.com";
const BASE_URL = "http://localhost:8083";

const $reservationList = document.querySelector('.reservation-list');

let currentPage = 1;
let isFetching = false; // 데이터 불러오는 중에는 더 가져오지 않도록 제어
let totalReservations = 0;
let loadedReservations = 0;

// =========== 함수 정의 ================
function appendReservations(reservations) {
    let tag = '';
    let statusInfo = '';

    if(reservations && reservations.length > 0){
        reservations.forEach(({reservationId , storeImg, storeName, status, pickupTime, pickedUpAt, cancelReservationAt, reservationTime}) => {

            let t1 = formatDate(pickupTime);
            let t2 = formatDate(pickedUpAt);
            let t3 = formatDate(cancelReservationAt);
            let t4 = formatDate(reservationTime);

            if (status === 'RESERVED') {
                statusInfo = `${t1}까지 픽업해주세요`;
            }
            if (status === 'PICKEDUP') {
                statusInfo = `${t2}에 픽업하셨습니다`;
            }
            if (status === 'CANCELED') {
                statusInfo = `${t3}에 취소하셨습니다`;
            }
            tag += `
            <div class="reservation-item" data-reservation-id="${reservationId}">
                <img src="${storeImg}" alt="Store Image"/>
                <span>${storeName}</span>
                <span>${status}</span>
                <span>${statusInfo}</span>
                ${status === 'RESERVED' ? '<button class="reservation-cancel-btn">예약 취소</button>' : ''}
            </div>
            `;
        });
    } else {
        tag = `<div class='reservation-list'>예약 내역이 없습니다.</div>`;
    }

    $reservationList.innerHTML = tag;
    console.log('appendReservations() 실행');
}

async function fetchReservations() {
    if (isFetching) return;
    isFetching = true;

    try {
        const res = await fetch(`${BASE_URL}/reservation/${customerId}`);
        const reservations = await res.json();
        console.log(reservations);
        appendReservations(reservations);

    } catch (error) {
        console.error('Error fetching reservations:', error);
    } finally {
        isFetching = false;
    }
}

function setupInfiniteScroll() {
    if(($reservationList.scrollHeight - $reservationList.scrollTop <= $reservationList.clientHeight + 50)
        && !isFetching) {
        fetchReservations();
    }
}

function removeInfiniteScroll() {
    $reservationList.removeEventListener('scroll', setupInfiniteScroll);
}

function formatDate(isoDate) {
    const date = new Date(isoDate);

    // 월 이름 매핑
    const months = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
    const monthName = months[date.getMonth()];

    // 시간 및 분 추출
    const hours = date.getHours();
    const minutes = ('0' + date.getMinutes()).slice(-2); // 두 자리로 맞추기 위해

    // 결과 문자열 구성
    const formattedDate = `${monthName} ${date.getDate()} / ${hours}시 ${minutes}분`;

    return formattedDate;
}

// 예약 취소
const fetchCancelReservation = async (reservationId) => {
    console.log(reservationId);
    const res = await fetch(`${BASE_URL}/reservation/${reservationId}/cancel`, {
        method: 'PATCH'
    });

    if (res.status !== 200) {
        alert('취소에 실패했습니다!');
        return;
    }

    $reservationList.scrollTo(0, 0); // 취소 후 페이지 상단으로 이동
    await fetchReservations();
};

// 예약 취소 처리 클릭 이벤트
function cancelReservationClickEvent() {
    $reservationList.addEventListener('click', e => {
        e.preventDefault();
        if (!e.target.matches('.reservation-cancel-btn')) return;

        if (!confirm('픽업시간 기준 1시간 이내로 예약 취소시 취소 수수료 50%가 부과됩니다. 정말 취소하시겠습니까?')) return;

        const reservationId = e.target.closest('.reservation-item').dataset.reservationId;

        fetchCancelReservation(reservationId);
    });
}

// =========== 함수 샐행 ================
document.addEventListener('DOMContentLoaded', () => {
    fetchReservations(); // 초기 예약 데이터 로드
    setupInfiniteScroll(); // 무한 스크롤 설정
    cancelReservationClickEvent(); // 예약 취소 이벤트 설정
});
