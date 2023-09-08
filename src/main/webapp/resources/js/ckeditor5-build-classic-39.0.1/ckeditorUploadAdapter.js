class ckeditorUploadAdapter {
	
  constructor(loader) {
	
	var realPath = '';
	
    // CKEditor5's FileLoader instance.
    this.loader = loader;
    // URL where to send files.
	// 컨트롤러 요청명 선언 부분
    this.url = '/ckeditorCon/upload.go';
	
	
	var os = '';
	var ua = navigator.userAgent;
	
	if (ua.match(/Win(dows )/)){
		this.realPath = 'c:/ckeditor_upload';
	} else { 
		this.realPath = '/home/ec2-user/ckeditor_FileDir';
	}
	
	console.log(' this.realPath : ' + this.realPath);
  }
  // Starts the upload process.
  upload() {
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
    const xhr = (this.xhr = new XMLHttpRequest());
    xhr.open("POST", this.url, true);
    xhr.responseType = "json";
  }
  // Initializes XMLHttpRequest listeners.
  _initListeners(resolve, reject) {
    const xhr = this.xhr;
    const loader = this.loader;
    const genericErrorText = "Couldn't upload file:" + ` ${loader.file.name}.`;
    xhr.addEventListener("error", () => reject(genericErrorText));
    xhr.addEventListener("abort", () => reject());
    xhr.addEventListener("load", () => {
      const response = xhr.response;
      console.log(response);
      if (!response || response.error) {
        return reject(
          response && response.error ? response.error.message : genericErrorText
        );
      }
      // If the upload is successful, resolve the upload promise with an object containing
      // at least the "default" URL, pointing to the image on the server.
      resolve({
        default: getDownloadUrl(response[0]),
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
    that.xhr.setRequestHeader("Authorization", authHeader().Authorization);
    file.then(function (result) {
      //wait for the promise to finish then continue
      data.append("upload", result);
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
function getDownloadUrl(item) {
  console.log(' this.realPath + item.linkId : ' + this.realPath + '${item.linkId}');
  return encodeURI(this.realPath + item.linkId);
}
