import React, {Component} from 'react';
import axios from 'axios'
import MyChart from "./MyChart";

class Tsp extends Component {
    state = {
        evaluations: [],
        fileNames: [],
        crossingAlgorithms: [
            {name: "partially matched crossover", value: "PMX"},
            {name: "cycle crossover", value: "CX"},
            {name: "ordered crossover", value: "OX"}
        ],
        selectionAlgorithms: [
            {name: "tournament", value: "TOUR"},
            {name: "roulette", value: "ROUL"}
        ],
        mutationAlgorithms: [
            {name: "swap", value: "SWAP"},
            {name: "inverse", value: "INVERSE"}
        ],
        initializers: [
            {name: "random", value: "RANDOM"},
            {name: "greedy", value: "GREEDY"},
            {name: "mixed", value: "MIXED"}
        ]
    }

    componentDidMount() {

        axios.get('http://localhost:8080/getFileNames', {
            headers: {
                'Access-Control-Allow-Origin': '*'
            }
        }).then(response => {
            this.setState({
                fileNames: response.data
            })
        })
    }

    render() {
        return (
            <div style={{padding: '20px'}}>
                Nazwa pliku:
                <select id="fileName" onChange={e => this.setState({fileName: e.target.value})}>
                    <option disabled selected></option>
                    {this.state.fileNames.map(name => (<option value={name}>{name}</option>))}
                </select>
                Algorytm selekcji:
                <select id="selection" onChange={e => this.setState({selectionAlgorithm: e.target.value})}>
                    <option disabled selected></option>
                    {this.state.selectionAlgorithms.map(item => (<option value={item.value}>{item.name}</option>))}
                </select>
                Algorytm kryżowania:
                <select id="crossing" onChange={e => this.setState({crossingAlgorithm: e.target.value})}>
                    <option disabled selected></option>
                    {this.state.crossingAlgorithms.map(item => (<option value={item.value}>{item.name}</option>))}
                </select>
                Algorytm mutowania:
                <select id="mutation" onChange={e => this.setState({mutationAlgorithm: e.target.value})}>
                    <option disabled selected></option>
                    {this.state.mutationAlgorithms.map(item => (<option value={item.value}>{item.name}</option>))}
                </select>
                Inicjalizer:
                <select id="initializer" onChange={e => this.setState({initializer: e.target.value})}>
                    <option disabled selected></option>
                    {this.state.initializers.map(item => (<option value={item.value}>{item.name}</option>))}
                </select>
                <br/>
                {this.state.selectionAlgorithm === "TOUR" && <i>tour:
                    <input type={'number'} onChange={e => this.setState({tour: e.target.value})}/></i>}
                Pop size: <input type={'number'} onChange={e => this.setState({pop_size: e.target.value})}/>
                Generations: <input type={'number'} onChange={e => this.setState({generations: e.target.value})}/>
                Px: <input type={'number'} onChange={e => this.setState({Px: e.target.value})}/>
                PM: <input type={'number'} onChange={e => this.setState({Pm: e.target.value})}/>
                <button onClick={this.start}>start</button>


                {this.state.evaluations && this.state.evaluations.length != 0 &&
                <MyChart evaluations={this.state.evaluations}/>}

            </div>
        );
    }

    start = () => {
        const {state} = this
        this.setState({isRunning: true})
        axios.post('http://localhost:8080/runProblem', {
            crossingAlgorithm: state.crossingAlgorithm,
            mutationAlgorithm: state.mutationAlgorithm,
            selectionAlgorithm: this.state.selectionAlgorithm,
            tour: this.state.tour,
            pop_size: this.state.pop_size,
            generations: this.state.generations,
            Px: this.state.Px,
            Pm: this.state.Pm,
            fileName: this.state.fileName,
            initializer: this.state.initializer
        }, {
            headers: {
                'Access-Control-Allow-Origin': '*'
            }
        }).then(response => {
            this.setState({evaluations: response.data, isRunning: false})
        })
        window.setTimeout(this.updateEvaluations, 50)
    }

    updateEvaluations = () => {
        axios.get('http://localhost:8080/evaluations', {
            headers: {
                'Access-Control-Allow-Origin': '*'
            }
        }).then(response => {
            this.setState({
                evaluations: response.data
            }, () => {
                if (this.state.isRunning)
                    window.setTimeout(this.updateEvaluations, 100)
            })
        })
    }
}

export default Tsp;
