import { useHistory } from "react-router-dom"

const Registrazione = () => {
    const history = useHistory()

    const vaiAPagina = (route) => {
        history.push(route)
    }

    return(
        <>
            <h3>Registrazione</h3>
            <label>Nome</label>
            <input type="text"/>
            <br/>
            <label>Cognome</label>
            <input type="text"/>
            <br/>
            <label>Email</label>
            <input type="text"/>
            <br/>
            <label>Password</label>
            <input type="text"/>
            <br/>
            <button>Invio</button>
            <br/>
            <button onClick={(e) => vaiAPagina('/')}>Indietro</button>
        </>
    )
}

export default Registrazione