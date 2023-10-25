<template>
        <nav class="navbar bg-dark navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">eCapital</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 text-light">
                        <li class="nav-item">
                            <router-link to="/" ><i class="bi bi-people-fill icon-margin icon-large"></i>Employee</router-link>
                        </li>
                        <li class="nav-item">
                            <router-link to="/"><i class="bi bi-telephone icon-margin icon-large"></i>Help</router-link>
                        </li>
                    </ul>
                    
                </div>
                <button class="btn btn-outline-success" type="submit">Sign In</button>
            </div>
        </nav>
</template>

<script>
export default {
    name: "HeaderNav",
    
}
</script>

<style>

.navbar-nav .nav-item {
    margin-right: 25px; 
}
.navbar-nav .nav-item a:hover,
.navbar-nav .nav-item router-link:hover {
    text-decoration: none;
}
.navbar-nav .nav-item a,
.navbar-nav .nav-item router-link {
    color: inherit; 
    font-size: 1.2rem; 
}
.icon-margin {
    margin-right: 6px;
}
.icon-large {
    font-size: 1.2rem; 
}

</style>