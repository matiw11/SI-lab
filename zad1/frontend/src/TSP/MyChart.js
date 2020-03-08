import React, {Component} from 'react';
import CanvasJSReact from '../canvas/canvasjs.react';

var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

class MyChart extends Component {
    render() {
        let min = []
        let averages = []
        let max = []
        const evaluations = this.props.evaluations
        let i = 0
        evaluations.forEach(evaluation => {
            min.push({x: i, y: evaluation.min});
            max.push({x: i, y: evaluation.max})
            averages.push({x: i, y: evaluation.average})
            i++;

        })
        const options = {
            height: 800,
            title: {
                text: ""
            },
            data: [{
                name: "min",
                type: "line",
                dataPoints: min,
                showInLegend: true
            },
                {
                    name: "max",
                    type: "line",
                    dataPoints: max,
                    showInLegend: true
                },
                {
                    name: "average",
                    type: "line",
                    dataPoints: averages,
                    showInLegend: true
                }]
        }


        return (
            <div >
                <br/>
                <CanvasJSChart options={options}/>
            </div>
        );
    }
}

export default MyChart;
