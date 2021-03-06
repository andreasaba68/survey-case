import SurveyTemplatesList from '../components/SurveyTemplatesList/SurveyTemplatesList';
import SurveyTemplateEditor from '../components/SurveyTemplateEditor/SurveyTemplateEditor';

import React, { Component } from 'react';
import { BrowserRouter, Route } from "react-router-dom";
import * as common from '../components/common.jsx';
import './App.scss';

export default class App extends Component {

  state = {
    backEndVersion: "querying .."
  }

  // version: "2019-09-25 09:05:25.379"
  componentDidMount() {
    const url = `/ops/version`; // "http://errorpioppolo/test"; // 
    common.fetchJson(url).then(d => this.setState(d))
      .catch(e => this.setState({ backEndVersion: `Error while loading the survey : ${e.message}` }));
  }

  render() {
    return (
      <BrowserRouter>
        <div>
          <header className="App">
            <p style={{ display: "inline-block" }}>Just a header to start with.</p>
            <p style={{ display: "inline-block", fontSize: "0.7em" }}>&nbsp;&nbsp;&nbsp;&nbsp;{new Date().toTimeString()} - BackEnd {this.state.backEndVersion}.</p>
          </header>
          <Route path="/" exact component={SurveyTemplatesList} />
          <Route path="/edit/:id" component={SurveyTemplateEditor} />
        </div>
      </BrowserRouter>
    );
  }
}
