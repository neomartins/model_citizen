/**
 * Created by hmartins@worth.systems on 01/06/2018.
 */
import React from 'react';

const Client = (props) => {
  return (
    <li className="col s4">
      <div className="card teal darken-1">
        <div className="card-content white-text">
          <span className="card-title">{props.note.name}</span>
          <p>{props.note.age}</p>
        </div>
      </div>
      <div className="card-action"></div>
    </li>
  )
};

export default Client;
