beginTime();
function beginTime() {
    let container = document.getElementById("time-container");
    changeTime(container);
    setInterval(()=>changeTime(container), 9000)
}
function changeTime(container) {
    let date = new Date();
    let days = date.getDate();
    days = days<10 ? "0"+days :days;
    let months = date.getMonth() + 1;
    months = months<10 ? "0"+months:months;
    let years = date.getFullYear();
    let hours = date.getHours();
    hours = hours<10 ? "0"+hours : hours;
    let minutes = date.getMinutes();
    minutes = minutes<10 ? "0"+minutes : minutes;
    let seconds = date.getSeconds();
    seconds = seconds<10 ? "0"+seconds : seconds;
    container.innerHTML = `<p>Дата: ${days}.${months}.${years}</p>
                            <p>Время: ${hours}:${minutes}:${seconds}</p>`
}