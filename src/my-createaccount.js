import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import '@polymer/paper-card/paper-card.js';
import '@polymer/paper-button/paper-button.js';
import '@polymer/paper-input/paper-input.js';
import '@polymer/paper-dropdown-menu/paper-dropdown-menu.js';
import '@polymer/paper-item/paper-item.js';
import '@polymer/paper-listbox/paper-listbox.js';
import '@polymer/iron-form/iron-form.js';
import '@polymer/paper-toast/paper-toast.js';
import '@vaadin/vaadin-grid/vaadin-grid.js';
// Import template repeater
import '@polymer/polymer/lib/elements/dom-repeat.js';


// Define the element's API using an ES2015 class
class LoginElement extends PolymerElement {

  // Define optional shadow DOM template
  static get template() {
    return html`
      <style>
        /* CSS rules for your element */
        paper-card{
          margin:4%;
          margin-left:6%;
          width:90%;
        }
        paper-card:hover{
         border-bottom: 3px solid #ff6200; 
        
         cursor:pointer;
        
        }
.customizedcss{
  background-color:rgba(0,0,0,0.8);
  color:#fff;

}
   
.customizedDangercss{
  background-color:red;
  color:rgba(255, 98, 0, 0.66);
  color:#fff;

}
  #actions{
    margin-top: 7%;
    margin-left: 27%;
    width: 30%;
    border:8px solid beige
  }     
      </style>

        <!-- shadow DOM for your element -->
   
 
  <!-- data bindings in shadow DOM -->
  

<paper-card >
<!--alert-dialog-->

<paper-dialog id="alertdialog">
  <h2>[[alertMsg]]</h2>
  <paper-button class="custom indigo customizedDangercss" style="float:right" dialog-confirm autofocus>OK</paper-button>
 </paper-dialog>
  <div class="card-content" style="background-color:beige">
  </div>
            <div class="card-actions"  style="margin:20px;border:5px solid beige; background-color:bisque">
              <p>Creeate New Account From</p>
              <div class="horizontal justified">
            <iron-form id="ceateAccountForm">
              <form>
                                <paper-input  value="{{firstName}}" always-float-label label="FirstName" error-message="Enter First Name" auto-validate required></paper-input>
                                <paper-input  value="{{lastName}}" always-float-label label="Password" error-message="Enter First Name" auto-validate required></paper-input>
                                <paper-input  value="{{emailId}}" always-float-label label="EmailId" error-message="Enter First Name" auto-validate required></paper-input>
                                <paper-input  value="{{pancard}}" always-float-label label="Pancard" error-message="Enter First Name" auto-validate required></paper-input>
                                <paper-input  value="{{contactNumber}}" always-float-label label="ContactNumber" error-message="Enter First Name" auto-validate required></paper-input>
                               
                            <paper-button class="custom indigo customizedcss"  
                            on-click="_submitCreateAccountForm">Create Account</paper-button>
                    
                            <paper-button toggles class="custom indigo customizedDangercss" class="green" on-click="_resetForm">Reset</paper-button>
                </form>
              
              </div>
  </div>
  <iron-ajax
  id="loginAjax"
  on-response="_responseHandler"
  on-error ="_errorHandler"
  debounce-duration="300">
</iron-ajax>  
    </iron-form> 
</paper-card>
    `;
  }

  // Declare properties for the element's public API
  static get properties() {
    return {
      creditStatus:{
        type:String,
        value :"Not Approved"
      },
      appliactionStatus:{
        type:String,
        value:"New"
      },
      greeting: {
        type: String
      },
      baseUrl:
      {
        type: String,
        value: "http://10.117.189.210:8090/app"
      },
      subUrl:
      {
        type: String,
        value: `/login`
      },
      alertMsg: {
        type: String,
        value: ""
      },
     
    }
  }

  constructor() {
    super();
    
  }
  connectedCallback(){
    super.connectedCallback();

  }
 
  _submitCreateAccountForm() {
    const isValidate = this.$.ceateAccountForm.validate();
   // console.log(this.$.ceateAccountForm.validate())
   //{firstName,lastName,accountNumber,pancard,emailId,contactNumber}
    if (isValidate) {
      const jsonBody = {
        firstName: this.firstName,
        password: this.password,
        accountNumber: this.accountNumber,
        pancard: this.pancard,
        emailId:this.emailId,
        emailId:this.emailId


        
      }
      let requiredData = {
        method: "post",
        url: `${this.baseUrl}/saveApplication`,
        body: jsonBody
      }
      this._makePostApiCall(requiredData);
    }
  }


  _makePostApiCall(data) {
    let ajaxRef = this.$.ajaxPost;
      ajaxRef.method = data.method;
      ajaxRef.url = data.url,
      ajaxRef.body = data.body;
      ajaxRef.contentType = "application/json"
      ajaxRef.generateRequest();
  }

  _postresponseHandler(event){
    const response = event.detail.response;
  }

  _resetForm() {
    this.$.ceateAccountForm.reset();
  }
  _responseHandler(event) {
    const response = event.detail.response;
  }

  _handleError() {
    alert('_handleError');
  }

}

// Register the x-custom element with the browser
customElements.define('login-element', LoginElement);