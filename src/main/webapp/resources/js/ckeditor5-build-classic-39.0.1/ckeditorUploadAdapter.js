class ckeditorUploadAdapter {
	
  constructor(loader, siteCode) {
	
	var realPath = '';
	
    // CKEditor5's FileLoader instance.
    this.loader = loader;
	this.siteCode = siteCode;
    // URL where to send files.
	// 컨트롤러 요청명 선언 부분
    this.url = '';
	
	
	var os = '';
	var ua = navigator.userAgent;
	
	if (ua.match(/Win(dows )/)){
		this.url = 'http://localhost:8081/third/ckeditorCon/upload.go';
	} else { 
		this.url = 'http://35.78.200.75:8081/third/ckeditorCon/upload.go';
	}
		this.realPath = '/ckeditor_upload/';
	
	console.log(' this.realPath : ' + this.realPath);
  }
  // Starts the upload process.
  upload() {
	console.log('upload 프로세스 실시');
    return new Promise((resolve, reject) => {
      this._initRequest();
      this._initListeners(resolve, reject);
      this._sendRequest();
    });
  }
  // Aborts the upload process.
  abort() {
    if (this.xhr) {
      this.xhr.abort();
    }
  }
  // Example implementation using XMLHttpRequest.
  _initRequest() {
	console.log('_initRequest 프로세스 실시');
    const xhr = (this.xhr = new XMLHttpRequest());
    xhr.open("POST", this.url, true);
    xhr.responseType = "json";
  }
  // Initializes XMLHttpRequest listeners.
  _initListeners(resolve, reject) {
	console.log('_initListeners 프로세스 실시');
    const xhr = this.xhr;
    const loader = this.loader;
    const genericErrorText = "Couldn't upload file:" + ` ${loader.file.name}.`;
    xhr.addEventListener("error", () => reject(genericErrorText));
    xhr.addEventListener("abort", () => reject());
    xhr.addEventListener("load", () => {
      const response = xhr.response;
      console.log(response);
      if (!response || response.error) {
		console.log("에러 발생");
        return reject(
          response && response.error ? response.error.message : genericErrorText
        );
      }
      // 만약 업로드가 성공했다면, 업로드 프로미스를 적어도 default URL을 담은 객체와 함께 resolve하라. 
      // 이 URL은 서버에 업로드된 이미지를 가리키며, 컨텐츠에 이미지를 표시하기 위해 사용된다.
      resolve({
        default: getDownloadUrl( this.realPath, response),
      });
    });
    if (xhr.upload) {
      xhr.upload.addEventListener("progress", (evt) => {
        if (evt.lengthComputable) {
          loader.uploadTotal = evt.total;
          loader.uploaded = evt.loaded;
        }
      });
    }
  }
  // Prepares the data and sends the request.
  _sendRequest() {
    const data = new FormData();
    let that = this;
    let file = that.loader.file;
     
    // set jwt token if required.
	//authHeader가 뭔데? --> 여기 예시에서 임의로 추가시킨 가상의 메소드, 말그대로 없어도된다
    // 여기가 인증이나 CSRF 방어와 같은 방어 로직을 작성하기 좋은 곳이다. 
    // 예를들어, XHR.setREquestHeader()를 사용해 요청 헤더에 CSRF 토큰을 넣을 수 있다.
    file.then(function (result) {
      //wait for the promise to finish then continue
		data.append("upload", result);
		data.append("siteCode", that.siteCode);
		//that.xhr.setRequestHeader("siteCode", $('#siteCode').val() );
		that.xhr.send(data);
    });
  }
}
 
/**
 * return full image url with responsed JSON object
 * 서버 경로에 기반해 url을 적어놓는 부분
	실제 파일이 저장된 위치를 따라감
 * @param {*} item 
 */
function getDownloadUrl(realPath, item) {
  console.log(' this.realPath + item.linkId : ' + realPath + item.url );
  return encodeURI(realPath + item.url);
}
