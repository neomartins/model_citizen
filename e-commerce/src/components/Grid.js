/**
 * Created by hmartins@worth.systems on 01/06/2018.
 */
import React, {Component} from 'react';
import Client from './Client';

class Grid extends Component {

  renderItems() {
    return this.props.clients.map(item => {
      return <Client
        key={item.id}
        note={item}
      />
    });
  }

  render() {
    return (
      <div className="row">
        <ul >
          {this.renderItems()}
        </ul>
      </div>
    );
  }
}

export default Grid;