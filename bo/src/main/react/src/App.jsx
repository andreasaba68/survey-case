import React, { Component } from 'react';
import * as common from './components/common.jsx';
import './App.css';

export default class App extends Component {

  state = {
    backEndVersion: "querying .."
  }

  // version: "2019-09-25 09:05:25.379"
  componentDidMount() {
    const url = `/ops/version`; // "http://errorpioppolo/test"; // 
    common.fetchJson(url).then(d => this.setState(d))
    .catch(e => this.setState({ error: `Error while loading the survey : ${e.message}` }));
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <p>BackEnd Version {this.state.backEndVersion}.</p>
        </header>
      </div>
    );
  }
}
