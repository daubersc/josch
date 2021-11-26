import {createRequire} from 'module'
import isJsonSchemaSubset from 'is-json-schema-subset';

const require = createRequire(import.meta.url);
const express = require('express')

const ijss = isJsonSchemaSubset.default
const bodyParser = require('body-parser');

const app = express()
const port = 8834
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

async function compare(schema1, schema2) {
    // Remove drafts because this tool only accepts draft-05 but draft-04 is given.
    schema1.$schema = null
    schema2.$schema = null


    // Do the comparison.
    const s1SubS2 = ijss(schema1, schema2);
    const s2SubS1 = ijss(schema2, schema1);
    if (s1SubS2 && s2SubS1) {
        return "equivalent"
    } else if (s1SubS2) {
        return "subset"
    } else if (s2SubS1) {
        return "superset"
    } else {
        return "incomparable"
    }
}

app.post('/compare', (req, res) => {
    const schema1 = req.body.s1
    const schema2 = req.body.s2
    
    if (schema1 === undefined) {
        throw new Error('schema 1 \'s1\' is missing in json. JSON should look like {"s1": ..., "s2": ...}.')
    }
    if (schema2 === undefined) {
        throw new Error('schema 2 \'s2\' is missing in json. JSON should look like {"s1": ..., "s2": ...}.')
    }

    compare(schema1, schema2).then(result => res.send(result))
})

app.listen(port, () => {
    console.log(`IJS-Web listening at http://127.0.0.1:${port}`)
})


