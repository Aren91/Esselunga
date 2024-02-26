import { useHistory } from "react-router-dom"

const Login = () => {
    const history = useHistory()

    const vaiAPagina = (route) => {
        history.push(route)
    }

    return(
        <>
            <h3>Login</h3>
            <button onClick={(e) => vaiAPagina('/')}>Indietro</button>
        </>
    )
}

export default Login