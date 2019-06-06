import {PolymerElement, html} from "@polymer/polymer/polymer-element.js"
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
class MyChart extends PolymerElement {
  connectedCallback(){
    super.connectedCallback();
   // this.drawGraph();
    this.getGraphData();
  }
  
  // Declare properties for the element's public API
  static get properties() {
    return {
      creditStatus:{
        type:String,
        value :"Not Approved"
      },
      baseUrl:{
        type:String,
        value: "http://10.117.189.210:8090/app"
      },
      graphData:{
        type:Array,
        value: []
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
      }
      
      
    }
  }

getGraphData(){
  let ajaxRef = this.$.graphDataAjax;
  ajaxRef.method = "get"
  ajaxRef.url = `${this.baseUrl}/getAuditDetail`,
  ajaxRef.contentType = "application/json"
  ajaxRef.generateRequest();

}
_getGraphDataHandler(event){
  var global =this;
  const response = event.detail.response;
  this.graphData=response;
  //   productMorgageList,productPaymentList,productSavingList,products
  this.graphData.filter(data =>{ 
    console.log(data);
    if(data.productGroupName=="Mortgage"){
      this.MortgageCount = data.count;
      global.push('productMorgageList',  {"productNameId":data.productNameId, "productName":data.productName});
    }else if(data.productGroupName=="Payment"){
      this.PaymentCount = data.count;
      global.push('productPaymentList',  {"productNameId":data.productNameId, "productName":data.productName});
    }else if(data.productGroupName=="Savings"){
      this.SavingsCount = data.count;
      global.push('productSavingList', {"productNameId":data.productNameId, "productName":data.productName});
    }
  });
  this.drawGraph();
}

  

drawGraph(){
  //alert(this.shadowRoot.querySelector('#pieContainer'));
    console.log("drawGraph");
  console.log(this.MortgageCount);
  console.log(this.SavingsCount);
  console.log(this.PaymentCount);
    var data = [{
      values: [this.MortgageCount, this.SavingsCount,this.PaymentCount],
      labels: ['Mortgage', 'Savings', 'Payment'],
      type: 'pie'
    }];
   /* var data = [{
      values: [19, 26, 55],
      labels: ['Residential', 'Non-Residential', 'Utility'],
      type: 'pie'
    }];*/
    console.log(this)
    Plotly.newPlot(this.shadowRoot.querySelector('#pieContainer'), data, {}, {showSendToCloud:true});
    
    //Plotly.newPlot('pieContainer', data, {}, {showSendToCloud:true});
  }
  static get template() {
    return html`
    <!-- scoped CSS for this element -->
    <style>
      div {
        display: inline-block;
        background-color: #ccc;
        border-radius: 8px;
        padding: 4px;
      }
      html, body, #container {
        width: 100%;
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
    <!--
    TODO: Try adding other HTML elements to the DOM template
    to see how they are positioned relative to the distributed
    child nodes.
    -->
    <div>
      <!-- any children are rendered here -->
    
      <div id="pieContainer"  style="margin-top: 54px;margin-left: 49%"><!-- Plotly chart will be drawn inside this DIV --></div>
 

   <iron-ajax
   id="graphDataAjax"
   on-response="_getGraphDataHandler"
   on-error ="_errorHandler"
   debounce-duration="300">
</iron-ajax>  
    </div>
    `;
  }
}
customElements.define('my-chart', MyChart);
