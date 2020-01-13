function drawAxis() {
    let h = canvas.height;
    let w = canvas.width;
    ctx.strokeStyle = "lime";
    ctx.lineWidth = 2;
    ctx.beginPath();
    ctx.moveTo(w/2, h);
    ctx.lineTo(w/2, 0);
    ctx.lineTo(w/2+3, 7);
    ctx.moveTo(w/2, 0);
    ctx.lineTo(w/2-3, 7);
    drawDigitsX(ctx, size, w, h);
    ctx.stroke();
    ctx.beginPath();
    ctx.moveTo(0, h/2);
    ctx.lineTo(w, h/2);
    ctx.lineTo(w-7, h/2+3);
    ctx.moveTo(w, h/2);
    ctx.lineTo(w-7, h/2-3);
    drawDigitsY(ctx, size, w, h);
    ctx.stroke();/*
    ctx.strokeStyle = "grey";
    ctx.lineWidth = 1;
    ctx.moveTo(w/2-5*i, h/2-3*i);
    ctx.lineTo(w/2+5*i, h/2-3*i);
    ctx.lineTo(w/2+5*i, h/2+5*i);
    ctx.lineTo(w/2-5*i, h/2+5*i);
    ctx.lineTo(w/2-5*i, h/2-3*i);
    ctx.stroke();*/
}
function drawDigitsX(ctx, i, w, h) {
    let t=w/2;
    for (let j=0; j<5; j++){
        t+=i;
        ctx.moveTo(t, h/2+3);
        ctx.lineTo(t, h/2-3)
    }
    t=w/2;
    for (let j=0; j<5; j++){
        t-=i;
        ctx.moveTo(t, h/2+3);
        ctx.lineTo(t, h/2-3)
    }
}
function drawDigitsY(ctx, i, w, h) {
    let t=h/2;
    for (let j=0; j<5; j++){
        t+=i;
        ctx.moveTo(w/2+3, t);
        ctx.lineTo(w/2-3, t);
    }
    t=h/2;
    for (let j=0; j<5; j++){
        t-=i;
        ctx.moveTo(w/2+3, t);
        ctx.lineTo(w/2-3, t);
    }
}
function drawArea(r) {
    let h = canvas.height;
    let w = canvas.width;
    ctx.strokeStyle = "#00ff00";
    ctx.fillStyle = "#00ff00";

    ctx.beginPath();
    //ctx.arc(w/2,h/2,100,0,Math.PI,true);
    ctx.arc(w/2,h/2,r*size,Math.PI*3/2,Math.PI,true);
    ctx.moveTo(w/2, h/2-r*size);
    ctx.lineTo(w/2, h/2-r/2*size);
    ctx.lineTo(w/2+r*size, h/2-r/2*size);
    ctx.lineTo(w/2+r*size, h/2);
    ctx.lineTo(w/2+r/2*size, h/2);
    ctx.lineTo(w/2, h/2+r*size);
    ctx.lineTo(w/2,h/2);
    ctx.lineTo(w/2-r*size, h/2);
    ctx.fill();
}
function drawPoint(x, y, color) {
    ctx.fillStyle = color;
    ctx.beginPath();
    ctx.arc(canvas.width/2+x*size,canvas.height/2-y*size,2,0,Math.PI*2, true);
    ctx.fill();
}

function drawPointsFromTable() {

    console.log("qwertyui");
    let table = document.getElementById("result-table");
    //if(document.getElementsByTagName("tbody")[0]){table = document.getElementsByTagName("tbody")[0]}
    if(table){
        for(let i=1; i<table.children.length; i++){
            let row = table.children[i];
            if(row.id!=="table-headers"&&Number(row.children[2].innerText)!==Number(rField.value)){
                console.log(Number(row.children[2].innerText));
                doAjax(row.children[0].innerText, row.children[1].innerText, rField, false)
            }
            else if(row.id!=="table-headers"){
                console.log(Number(row.children[2].innerText));
                drawPoint(Number(row.children[0].innerText), Number(row.children[1].innerText), (row.children[3].innerText==="Да" ? "lime":"red"));
            }
        }
    }
}

function clearCanvas() {
    canvas.getContext('2d').clearRect(0, 0, canvas.width, canvas.height);
}
function drawResize(radius) {
    table = document.getElementById("table-form:result-table").childNodes[3];
    counter = 0;
    console.log("RADIUS   "+radius);
    RRR.value = radius;
    drawStep(radius);
}

function drawStep(radius) {
    console.log(counter);
    if (counter < table.children.length){
        let row = table.children[counter];
        if(!row.children[0].innerText) return;
        let x = Number(row.children[0].innerText);
        console.log(x);
        let y = Number(row.children[1].innerText);
        console.log(y);
        xCanvas.value = x;
        yCanvas.value = y;
        counter++;
        checkResize();
    }
}