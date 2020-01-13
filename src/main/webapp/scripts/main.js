let canvas = document.getElementById('canvas');
let ctx = canvas.getContext('2d');
let size = 25;
let radius = 3;
let yyy = -2;
let xxx = -10;
let xCanvas = document.getElementById("canvas-form:xCanvas");
let yCanvas = document.getElementById("canvas-form:yCanvas");
let RRR = document.getElementById("canvas-form:RRR");
RRR.value = radius;
let XXX = document.getElementById("canvas-form:XXX");
let YYY = document.getElementById("canvas-form:YYY");
YYY.value = yyy;
let counter;
let table = document.getElementById("table-form:result-table").childNodes[3];
handleRChange();
drawArea(radius);
drawAxis();
setTimeout(()=>drawResize(radius),10);

function handleRChange(event) {
    clearCanvas();
    if (event != null){radius = Number(event.target.value);}
    console.log(yyy);
    YYY.value = yyy;
    console.log(xxx);
    if (xxx !== -10){XXX.value = xxx;}
    console.log(radius);
    drawArea(radius);
    drawAxis();
    /*if (xxx!==-10){
        document.getElementById("submit").removeAttribute("disabled");
    }*/
    setTimeout(()=>drawResize(radius), 10);
}

function handleXChange(event){
    xxx = Number(event.target.value);
    XXX.value = Number(event.target.value);
    YYY.value = yyy;
    RRR.value = radius;
}
function handleYChange(event) {
    yyy = Number(event.target.value);
    YYY.value = Number(event.target.value);
    if (xxx !== -10){XXX.value = xxx;}
    RRR.value = radius;

}

function checker0(event){
    console.log("\n\n\n");
    XXX.value = xxx;
    console.log(xxx);
    YYY.value = yyy;
    console.log(yyy);
    RRR.value = radius;
    console.log(radius);
    setTimeout(()=>check(), 1000);

}

function handleSubmit() {
    console.log("handlesubmit");
    clearCanvas();
    drawArea(radius);
    drawAxis();
    drawResize(radius);
}

function isPointInArea(x,y,r) {
    return ((x<=0 && y>=0 && y<=(2*x+r)) || (x>=0 && y>=0 && x*x+y*y<=r*r) || (x>=0 && y<=0 && x<=r && y>=-r/2));
}

function handleCanvasClick(event) {
    let obj = event.target;
    let x = Number(((event.pageX - window.pageXOffset - obj.getBoundingClientRect().x - obj.width/2)/size).toFixed(2));
    let y = Number((-(event.pageY - window.pageYOffset - obj.getBoundingClientRect().y - obj.height/2)/size).toFixed(2));
    if(x>=-5 && x<=5 && y>=-5 && y<=5){
        xCanvas.value = x;
        yCanvas.value = y;
        console.log("zxc");
        checkCanvas();
    }
}
