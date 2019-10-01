import { Link } from "react-router-dom";

import React, { Component } from 'react';
import * as common from '../common.jsx';
import './SurveyTemplatesList.scss';

export default class SurveyTemplatesList extends Component {

  state = {
    error: null,
    templates: null
  }

  componentDidMount() {
    const url = "/bo/svc/templates";
    common.fetchJson(url).then(d => {this.setState({"templates": d}); })
    .catch(e => this.setState({ error: `Error while loading the Survey Templates List : ${e.message}` }));
  }

  render() {

    if (!this.state.templates)
      return common.progress();

    return (
      <div>
        {common.isError(this.state.error)}
        <div className="surveys-list">
          { 
            this.state.templates.map((t, i) => 
            <div key={t.id}>
              <div>
                <h4>Survey Template</h4>
                <Link to={`/edit/${t.id}`}><i className="fas fa-edit" /></Link>
                <button><i className="fas fa-minus-circle" /></button>
              </div>

              <div>
                <p>{t.title}</p>
              </div>

            </div>
          )
          }
          <span>
            <button><i className="fas fa-plus-circle fa-4x "/></button>
          </span>
        </div>
      </div>
    );
  }
}
