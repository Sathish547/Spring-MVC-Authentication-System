function uGetTime() {
	var dt=new Date();
	var h=dt.getHours();
	var m=dt.getMinutes();
	var s=dt.getSeconds();
	h = h > 9 ? h : "0" + h;
	m = m > 9 ? m : "0" + m;
	s = s > 9 ? s : "0" + s;
	var t=h + ":" + m + ":" + s;
	return t;
}