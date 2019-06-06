import { PolymerElement, html } from '@polymer/polymer/polymer-element.js';
import '@polymer/paper-card/paper-card.js';
import '@polymer/paper-button/paper-button.js';
import '@polymer/paper-input/paper-input.js';
import '@polymer/paper-dropdown-menu/paper-dropdown-menu.js';
import '@polymer/paper-item/paper-item.js';
import '@polymer/paper-listbox/paper-listbox.js';
import '@polymer/iron-form/iron-form.js';
import '@polymer/paper-toast/paper-toast.js';
//import '@vaadin/vaadin-grid/vaadin-grid.js';
// Import template repeater
import '@polymer/polymer/lib/elements/dom-repeat.js';

import '@polymer/iron-collapse/iron-collapse.js';
import '@polymer/paper-badge/paper-badge.js';


// Define the element's API using an ES2015 class
class DashboardElement extends PolymerElement {

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
  #alertdialog{
    color:#ff6200!important;
    min-width: 31%;
    margin-top:-28%;
    position: fixed;
    border: 6px solid beige;
  }
  #createdAccountDetailsDialog{
    color:#ff6200!important;
    border: 6px solid beige;

  }
  iron-collapse {
    border: 1px solid #dedede;
    border-top: none;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px;
    @apply --shadow-elevation-2dp;
  }
  paper-item{
    background-color:cadetblue;
    color:white;
    height:12px;
    border-bottom: 2px solid red;
  } 
</style>

      
<paper-card >
<!--alert-dialog-->

<paper-dialog id="alertdialog">
  <h2>[[alertMsg]]</h2>
  <paper-button class="custom indigo customizedDangercss" style="float:right" dialog-confirm autofocus>OK</paper-button>
 </paper-dialog>
  <div class="card-content" style="background-color:beige">
  Welcome To ING Portal
  </div>
 
          <div class="card-actions"  style="margin:20px;border:5px solid beige; background-color:bisque">
              <p>Product ING</p>
              <paper-tabs selected="0">
              <paper-tab id="productGroupName1"  data-item$="Mortgage" on-click="_switchBetweenLoginAndCreate">
             Mortgage | [[MortgageCount]]
            
              </paper-tab>
              <paper-tab id="productGroupName2" data-item$="Payments" on-click="_switchBetweenLoginAndCreate">
              Payments | [[PaymentCount]]
              </paper-tab>
              <paper-tab id="productGroupName3" data-item$="Savings" on-click="_switchBetweenLoginAndCreate">
              Savings | [[SavingsCount]]
              
              </paper-tab>
                
      </paper-tabs>
              <!--
              <div class="horizontal justified">
              {{collapseId}}
              <button id="[[triggerId]]" on-click="toggle" aria-expanded\$="[[opened]]" aria-controls="collapse">[[_getText(opened)]]</button>
              <iron-collapse id="{{collapseId}}" opened="{{opened}}" horizontal="[[horizontal]]" no-animation="[[noAnimation]]" tabindex="0">
                <slot></slot>
                      Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea, id minim maiestatis incorrupte duo. Dolorum verterem ad ius, his et nullam verterem. Eu alia debet usu, an doming tritani est. Vix ad ponderum petentium suavitate, eum
                      eu tempor populo, graece sententiae constituam vim ex. Cu torquatos reprimique neglegentur nec, voluptua periculis has ut, at eos discere deleniti sensibus. Lorem ipsum dolor sit amet, per in nusquam nominavi periculis, sit elit oportere ea,
                      id minim maiestatis incorrupte duo. Dolorum verterem ad ius, his et nullam verterem. Eu alia debet usu, an doming tritani est. Vix ad ponderum petentium suavitate, eum eu tempor populo, graece sententiae constituam vim ex. Cu torquatos reprimique
                      neglegentur nec, volu
              </iron-collapse>  
          
        </div> --> 
    

    <!--Account Creation Form start-->
    <template is="dom-if" if="{{showSelectedGroupProductlist}}">
    <div class="card-actions"  style="margin:20px;border:5px solid beige; background-color:azure;padding:8%">
            <p>[[prodctGroupName]]</p>
            <div class="horizontal justified">
            <div role="listbox">
            <template is="dom-repeat" items="{{products}}">
             <paper-item id="[[item.productNameId]]" on-click="_showProductDetail"><small> </small>[[item.productName]]</paper-item>  
            <div>
            
          </template>
               <template is="dom-if" if="{{showSelectedproductDetails}}">
                  <div class="card-actions"  style="margin:20px;border:1px solid gray; background-color:ivory;padding:8%">
                          <p>Product Details:</p>
                          <div class="horizontal justified">
                          <div>ProductName |  <small>[[selectedProductDetails.productName]]</small></div>

                          <div>Percentage | <small>[[selectedProductDetails.percentage]]</small></div>

                          <div>PutInAndWithdrawel |  <small>[[selectedProductDetails.putInAndWithdrawel]]</small></div>
                          <div>MinimalInvestment |  <small>[[selectedProductDetails.minimalInvestment]]</small></div>
                        
                         
                          <div>MaxInvestment |  <small>[[selectedProductDetails.maxInvestment]]</small></div>
                          <div>Duration |  <small>[[selectedProductDetails.duration]]</div>
                        
                          <div>ProductGroupName |  <small>[[selectedProductDetails.productGroup.productGroupName]]</small></div>
                          
                      
                    </div>
                  </template>
          </div>
         
      </div>
    </template>
    <!--Account Creation Form End-->

  </div>


 
</div>
                <iron-ajax
                id="getGroupCount"
                on-response="_getGroupCountHandler"
                on-error ="_errorHandler"
                debounce-duration="300">
                </iron-ajax>  


              <iron-ajax
                      id="getAllProductGroupAjax"
                      on-response="_getAllProductGroupHandler"
                      on-error ="_errorHandler"
                      debounce-duration="300">
              </iron-ajax>  
  <iron-ajax
                      id="getAllProductGroupAjax"
                      on-response="_getAllProductGroupHandler"
                      on-error ="_errorHandler"
                      debounce-duration="300">
              </iron-ajax>  

             
              <iron-ajax
                id="getProductDetailsAjax"
                on-response="_getProductDetailsHandler"
                on-error ="_errorHandler"
                debounce-duration="300">
            </iron-ajax> 
            <iron-ajax
            id="updateOverviewCountAjax"
            handle-as="text"
            on-response="_getupdateOverviewCountHandler"
            on-error ="_errorHandler"
            debounce-duration="300">
        </iron-ajax> 


      
        <iron-ajax
        id="getUpdate"
        handle-as="text"
        on-response="_getUpdateHandler"
        on-error ="_errorHandler"
        debounce-duration="300">
    </iron-ajax> 

     
<!--Noraml Alert dialog-->
    <paper-dialog id="alertdialog">
            <h2>[[alertMsg]]</h2>
            <paper-button class="custom indigo customizedDangercss" style="float:right" dialog-confirm autofocus>OK</paper-button>
  </paper-dialog>

  <!--Details  Dialog Start-->
<paper-dialog id="createdAccountDetailsDialog">
                <div><h2 style="color:rgba(255, 98, 0, 0.66);">Created Account Details:</h2></div>
                <div>AccountId<span>: [[createdAccountDetails.account.accountId]]</span></div>
                <div>LoggedIn User Id<span>: [[createdAccountDetails.loginId]]</span></div>
                <div>Password has been sent to your registered email id. Please Check</span></div>
            <div class="buttons">
                  <paper-button dialog-dismiss>OK</paper-button>
                <!-- <paper-button dialog-confirm autofocus>Accept</paper-button>-->
                </div>
</paper-dialog>

<!--Details  Dialog End-->
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
     
      subUrl:
      {
        type: String,
        value: `/login`
      },
      alertMsg: {
        type: String,
        value: ""
      },
      showLoginForm:{
        type:Boolean,
        value:true
      },
      showSelectedGroupProductlist:{
        type:Boolean,
        value:false
      },
      createdAccountDetailsDialog:{
        type:Object, 
        value:{}
      },
      allProdu:{
        type:Array, 
        value:[]
      },
      baseUrl:{
        type:String,
        value: "http://10.117.189.210:8090/app"
      },
      triggerId:{
        type:String,
        value:""
      },
      collapseId:{
        type:String,
        value:"colid"
      },//SavingsCount,MortgageCount, MortgageCount
      SavingsCount:{
        type:Number,
        value:0
      },
      MortgageCount:{
        type:Number,
        value:0
      },
      PaymentCount:{
        type:Number,
        value:0
      },
   
      productMorgageList:{
        type:Array,
        value:[]
      },
      productPaymentList:{
        type:Array,
        value:[]
      },
      productSavingList:{
        type:Array,
        value:[]
      },
      showSelectedproductDetails:{
        type:Boolean,
        value:false
      },
      products:{
        type:Array,
        value:[]
      },
      selectedProductDetails:{
        type:Object,
        value:{}
      },
      productUniqId:{
        type:Number,
        value:0
      }
      //this.productUniqId
      
    }
  }
  constructor() {
    super();
    
  }
  connectedCallback(){
        super.connectedCallback();
        this._getAllProductGroup();
        this._getupdateOverview();

  }
  _getupdateOverview(){
    let ajaxRef = this.$.updateOverviewCountAjax;
    ajaxRef.method = "put"
    ajaxRef.url = `${this.baseUrl}/updateOverviewCount`,
    ajaxRef.contentType = "application/json"
    ajaxRef.generateRequest();

  }
  //   updateOverviewCountAjax,_getupdateOverviewCountHandler
  _getupdateOverviewCountHandler(event){
    const response = event.detail.response;
  }

  _switchBetweenLoginAndCreate(event){//Savings,Payments,Mortgage
    console.log('_switchBetweenLoginAndCreate', event.target.dataset.item$);
    let type=event.target.dataset.item$
    this.showSelectedGroupProductlist=true;
    this.prodctGroupName=type;
    if(type=="Mortgage"){
            // this.productList.
          // productMorgageList,productPaymentList,productSavingList,products
          this.products = this.productMorgageList;
    }else if(type=="Payments"){
      this.products = this.productPaymentList;

    }else{
      this.products = this.productSavingList;
    }
    
  }
  //showSelectedproductDetails,_showProductDetail
  _showProductDetail(event){
    //alert('showSelectedproductDetails'+event.target.id);
    let productId=event.target.id;
    this._getProductDetails(productId);
   
  }
  toggle() {
    let collapse= this.$.collapseId;
   // this.$.collapseId.toggle();
    collapseId.toggle();
  }
  _getText(opened) {
    return opened ? 'Collapse' : 'Expand';
  }
  //http://192.168.43.22:8090/app
  _getAllProductGroup(){//getAllProductGroupAjax
    let ajaxRef = this.$.getAllProductGroupAjax;
    ajaxRef.method = "get"
    ajaxRef.url = `${this.baseUrl}/getAllProductGroup`,
    ajaxRef.contentType = "application/json"
    ajaxRef.generateRequest();
  }
  _getAllProductGroupHandler(event){
    var global =this;
    const response = event.detail.response;
    this.allProductGroup= response;
    let productGroupName= response;
    this.prodctGroupName = "Mortgage";
   
    //   productMorgageList,productPaymentList,productSavingList,products
    productGroupName.filter(data =>{
      console.log(data);
      if(data.productGroup.productGroupName=="Mortgage"){
        this.MortgageCount = this.MortgageCount+1;
        global.push('productMorgageList',  {"productNameId":data.productNameId, "productName":data.productName});
      }else if(data.productGroup.productGroupName=="Payments"){
        this.PaymentCount = this.PaymentCount+1;
        global.push('productPaymentList',  {"productNameId":data.productNameId, "productName":data.productName});
      }else{
        this.SavingsCount =this.SavingsCount+1;
        global.push('productSavingList', {"productNameId":data.productNameId, "productName":data.productName});
      }
    });
    
    
  }//SavingsCount,MortgageCount, MortgageCount
  filterCount(data) {
    
  }
  // getProductDetailsAjax,_getProductDetailsHandler, this.showSelectedproductDetails= true; //!this.showSelectedproductDetails;
  _getProductDetails(productId){
   // alert("productId"+productId);
    this.productUniqId=productId;
    let ajaxRef = this.$.getProductDetailsAjax;
    ajaxRef.method = "get"
    ajaxRef.url = `${this.baseUrl}/getProductDetails/${this.productUniqId}`,
    ajaxRef.contentType = "application/json"
    ajaxRef.generateRequest();
  }
  _getProductDetailsHandler(event){
    const response = event.detail.response;
    if(response){
      this.selectedProductDetails= response;
      this.showSelectedproductDetails=true;
      this.getAllProductCount();
    }else{
      alert("No Data Found");

    }
         
   
  }
  getAllProductCount(){
    let productUniqId=productId;
    let ajaxRef = this.$.getUpdate;
    ajaxRef.method = "get"
    ajaxRef.url = `${this.baseUrl}/updateProductGroupCount/${this.productUniqId}`,
    ajaxRef.contentType = "application/json"
    ajaxRef.generateRequest();

  }
  _getUpdateHandler(event){

  }

  _errorHandler(){
    alert('error');
  }

  

  _handleError() {
    alert('_handleError');
  }

}

// Register the x-custom element with the browser
customElements.define('dashboard-element', DashboardElement);