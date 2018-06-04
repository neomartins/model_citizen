/**
 * Created by hmartins@worth.systems on 01/06/2018.
 */
import React, {Component} from 'react';

class Form extends Component {

  render() {
    return (
      <form onSubmit={this.props.handleSubmit} className="col s12">
        <div className="row">
          <div className="input-field col s3">
            <input placeholder="Name" name="currentName" type="text" value={this.props.currentName}
                   onChange={this.props.handleChange}/>
          </div>
          <div className="input-field col s3">
            <input placeholder="Age" name="currentAge" type="text" value={this.props.currentAge}
                   onChange={this.props.handleChange}/>
          </div>
          <div className="input-field col s2">
            <input placeholder="BirthDate" name="currentBirth" type="text" value={this.props.currentBirth}
                   onChange={this.props.handleChange}/>
          </div>
          <div className="input-field col s2">
            <button className="btn-large waves-effects waves-light" type="submit" name="action">Add client</button>
          </div>
        </div>
      </form>
    );
  }
}

export default Form;