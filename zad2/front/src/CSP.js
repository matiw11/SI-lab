import axios from "axios";
import React, {Component} from "react";
import './App.css';
class CSP extends Component {
    state = {
        number: 0,
        result: {}
    }

    render () {
        return (
            <div style={{position: "absolute",
                left: "50%",
                marginLeft: "-50px"}}>
                <input type="number" value={this.state.number} onChange={this.handleNumber}/>
                <button onClick={this.previewSudoku}>pokaz</button>
                <button onClick={this.startSudoku}>start</button>
                <br/>
                <div>
                <table>
                    {this.state.preview && this.state.preview.map(el =>(
                        <tr>
                            {el.map(e => (<td>{e !== 0? e: " "}</td>))}
                        </tr>
                    ))}
                </table>
                </div>
                <br/>
                <br/>
                <div>
                <table>
                    {this.state.result.sudoku && this.state.result.sudoku.map(el =>(
                        <tr>
                            {el.map(e => (<td>{e}</td>))}
                        </tr>
                    ))}
                </table>
                </div>
                <br/>
                odwiedzone węzły do pierwszego roziwazania : {this.state.result.visited}
                <br/>
                nawroty do pierwszego rozwiazania : {this.state.result.backed}
                <br/>
                czas znalezienia 1 roziwązania : {this.state.result.time} ms
                <br/>
                czas znalezienia wszystkich rozwiązań: {this.state.result.allTime} ms
                <br/>
                odwiedzone wezly ogolnie: {this.state.result.allVisited}
                <br/>
                nawroty ogolnie: {this.state.result.allBacked}
                <br/>
                liczba rozwiazan: {this.state.result.solutions}
            </div>


        );
    }

    previewSudoku = () =>{
        axios.get("http://localhost:8080/get/" + this.state.number)
            .then(response => this.setState({preview: response.data}));
    }

    startSudoku = () =>{
        axios.get("http://localhost:8080/start/" + this.state.number)
            .then(response => this.setState({result: response.data}));
    }
    handleNumber = (e) => {
        this.setState({number: e.target.value})
    }
}

export default CSP
